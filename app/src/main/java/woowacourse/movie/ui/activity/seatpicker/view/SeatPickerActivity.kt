package woowacourse.movie.ui.activity.seatpicker.view

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.alarm.AlarmManager
import woowacourse.movie.broadcastreceiver.NotificationReceiver
import woowacourse.movie.data.entity.Reservations
import woowacourse.movie.data.entity.Seats
import woowacourse.movie.ui.activity.MovieTicketActivity
import woowacourse.movie.ui.activity.seatpicker.SeatPickerContract
import woowacourse.movie.ui.activity.seatpicker.presenter.SeatPickerPresenter
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.seat.SeatModel
import woowacourse.movie.ui.utils.getParcelable
import woowacourse.movie.ui.utils.getParcelableByKey
import java.time.ZoneId
import java.time.ZonedDateTime

class SeatPickerActivity : AppCompatActivity(), SeatPickerContract.View {
    override lateinit var presenter: SeatPickerContract.Presenter
    private val seats = Seats().getAll()
    private val seatTable = mutableMapOf<SeatModel, TextView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        presenter = SeatPickerPresenter(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadSavedData(savedInstanceState)

        setDoneButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val ticket = presenter.getTicketModelWithOriginalPrice()
        outState.putParcelable(TICKET_INSTANCE_KEY, ticket)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun applyTicketData(ticket: MovieTicketModel) {
        setSeatViews(ticket)
        setTicketViews(ticket)
    }

    override fun setSeatReserved(seat: SeatModel) {
        val view = seatTable[seat]
        view?.let {
            setSeatViewSelected(it)
        }
    }

    override fun setSeatCanceled(seat: SeatModel) {
        val view = seatTable[seat]
        view?.let {
            it.isSelected = false
            it.setBackgroundColor(getColor(R.color.white))
        }
    }

    override fun updatePrice(price: Int) {
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        priceView.text = price.formatPrice()
    }

    override fun notifyUnableToReserveMore() {
        Toast
            .makeText(
                this,
                getString(R.string.toast_message_seat_selection_done),
                Toast.LENGTH_SHORT
            )
            .show()
    }

    override fun deactivateDoneButton() {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_off))
        doneButton.isClickable = false
    }

    override fun activateDoneButton() {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        doneButton.setBackgroundColor(getColor(R.color.seat_picker_done_button_on))
        doneButton.isClickable = true
    }

    private fun setSeatViewSelected(view: TextView) {
        view.isSelected = true
        view.setBackgroundColor(getColor(R.color.seat_selected))
    }

    private fun Int.formatPrice(): String = getString(R.string.price, this)

    private fun setSeatViews(ticket: MovieTicketModel) {
        val seatViews = findViewById<TableLayout>(R.id.layout_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

        seatViews.zip(seats) { view, seat ->
            seatTable[seat] = view
            view.text = getString(R.string.seat, seat.row.letter, seat.column.value)
            view.setTextColor(getColor(seat.rank.color))
            if (ticket.isSelectedSeat(seat)) setSeatViewSelected(view)
            view.setOnClickListener {
                selectSeat(view, seat)
                presenter.checkSelectionDone()
            }
        }
    }

    private fun selectSeat(
        view: TextView,
        seat: SeatModel
    ) {
        if (view.isSelected) {
            presenter.cancelSeat(seat)
            return
        }
        presenter.reserveSeat(seat)
    }

    private fun setTicketViews(ticket: MovieTicketModel) {
        val titleView = findViewById<TextView>(R.id.seat_picker_title)
        val priceView = findViewById<TextView>(R.id.seat_picker_price)
        titleView.text = ticket.title
        priceView.text = ticket.price.amount.formatPrice()
    }

    private fun setDoneButton() {
        val doneButton = findViewById<TextView>(R.id.seat_picker_done_button)
        doneButton.setOnClickListener {
            showReservationCheckDialog()
        }
        presenter.checkSelectionDone()
    }

    private fun showReservationCheckDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title_seat_selection_check))
            .setMessage(getString(R.string.dialog_message_seat_selection_check))
            .setPositiveButton(getString(R.string.dialog_positive_button_seat_selection_check)) { _, _ ->
                val ticketModel = presenter.getTicketModel()
                Reservations.addItem(ticketModel)
                setAlarm(ticketModel)
                moveToTicketActivity(ticketModel)
            }
            .setNegativeButton(getString(R.string.dialog_negative_button_seat_selection_check)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun setAlarm(ticketModel: MovieTicketModel) {
        val time =
            ZonedDateTime.of(
                ticketModel.time.notificationTime,
                ZoneId.systemDefault()
            )
                .toInstant().toEpochMilli()
        val intent = NotificationReceiver.createIntent(this, ticketModel)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            ticketModel.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        AlarmManager.setAlarm(this, time, pendingIntent)
    }

    private fun moveToTicketActivity(ticketModel: MovieTicketModel) {
        val intent = MovieTicketActivity.createIntent(this, ticketModel)
        startActivity(intent)
    }

    private fun loadSavedData(savedInstanceState: Bundle?) {
        val ticket = savedInstanceState?.getParcelableByKey<MovieTicketModel>(
            TICKET_INSTANCE_KEY
        )
            ?: intent.getParcelable(TICKET_EXTRA_KEY)!!
        presenter.initTicket(ticket)
    }

    companion object {
        private const val TICKET_EXTRA_KEY = "ticket_extra"
        private const val TICKET_INSTANCE_KEY = "ticket_instance"

        fun createIntent(context: Context, ticket: MovieTicketModel): Intent {
            val intent = Intent(context, SeatPickerActivity::class.java)
            intent.putExtra(TICKET_EXTRA_KEY, ticket)
            return intent
        }
    }
}