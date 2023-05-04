package woowacourse.movie.data.entity

import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieModel
import woowacourse.movie.ui.model.TheaterModel
import java.time.LocalDate
import java.time.LocalTime

class Movies {
    private val items: List<MovieModel> = listOf(
        MovieModel(
            R.drawable.parasite,
            "기생충",
            LocalDate.of(2023, 9, 12),
            LocalDate.of(2023, 9, 30),
            131,
            "직업도 없이 허름한 반지하에 사는 기택 가족에게 돈을 벌 기회가 찾아온다. 친구의 소개로 부잣집 딸 다혜의 과외 선생님을 맡게 된 기택의 아들, 기우는 기대감에 부푼 채 글로벌 IT기업을 이끄는 박 사장의 저택에 들어간다.",
            listOf(
                TheaterModel("선릉", listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(15, 0))),
                TheaterModel("잠실", listOf(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(14, 0), LocalTime.of(20, 0), LocalTime.of(22, 0))),
                TheaterModel("아주아주아주아주아주 긴 이름", listOf(LocalTime.of(18, 0), LocalTime.of(20, 0))),
            )
        ),
        MovieModel(
            R.drawable.about_time,
            "About Time",
            LocalDate.of(2023, 4, 10),
            LocalDate.of(2023, 4, 24),
            123,
            "아버지에게 가문 대대로 시간을 돌리는 능력을 타고났다는 사실을 들은 팀. 우연히 만난 메리에게 반한 팀은 완벽한 사랑을 위해 능력을 마음껏 사용하고, 그럴 때마다 주변 상황들이 점점 어긋나기 시작한다.",
            listOf(
                TheaterModel("선릉", listOf(LocalTime.of(15, 0), LocalTime.of(17, 0), LocalTime.of(19, 0))),
                TheaterModel("아주아주아주아주아주 긴 이름", listOf(LocalTime.of(12, 0), LocalTime.of(14, 0))),
            )
        ),
        MovieModel(
            R.drawable.witch,
            "마녀",
            LocalDate.of(2023, 8, 12),
            LocalDate.of(2023, 8, 29),
            125,
            "10년 전 의문의 사고가 일어난 시설에서 홀로 탈출한 후 모든 기억을 잃은 ‘자윤’. 나이도, 이름도 모르는 자신을 거두고 키워준 노부부의 보살핌으로 씩씩하고 밝은 여고생으로 자라났다. 어려운 집안사정을 돕기 위해 상금이 걸린 오디션 프로그램에 출연한 자윤, 방송이 나간 직후부터 의문의 인물들이 그녀 앞에 나타난다. " +
                "자윤의 주변을 맴돌며 날카롭게 지켜보는 남자 ‘귀공자’, 그리고 과거 사고가 일어난 시점부터 사라진 아이를 찾던 ‘닥터 백’과 ‘미스터 최’까지 자신은 전혀 기억하지 못하는 그들의 등장으로, 자윤은 혼란에 휩싸이게 되는데...! 그들이 나타난 후 모든 것이 바뀌었다.",
            listOf(
                TheaterModel("잠실", listOf(LocalTime.of(14, 0), LocalTime.of(16, 0), LocalTime.of(18, 0))),
            )
        ),
        MovieModel(
            R.drawable.avatar2,
            "아바타: 물의 길",
            LocalDate.of(2023, 10, 10),
            LocalDate.of(2023, 10, 20),
            192,
            "<아바타: 물의 길>은 판도라 행성에서 '제이크 설리'와 '네이티리'가 이룬 가족이 겪게 되는 무자비한 위협과 살아남기 위해 떠나야 하는 긴 여정과 전투, 그리고 견뎌내야 할 상처에 대한 이야기를 그렸다. " +
                "월드와이드 역대 흥행 순위 1위를 기록한 전편 <아바타>에 이어 제임스 카메론 감독이 13년만에 선보이는 영화로, 샘 워싱턴, 조 샐다나, 시고니 위버, 스티븐 랭, 케이트 윈슬렛이 출연하고 존 랜도가 프로듀싱을 맡았다.",
            listOf(
                TheaterModel("강남", listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(15, 0))),
                TheaterModel("잠실", listOf(LocalTime.of(10, 0), LocalTime.of(20, 0), LocalTime.of(22, 0))),
            )
        ),
        MovieModel(
            R.drawable.extreme_work,
            "극한직업",
            LocalDate.of(2023, 9, 12),
            LocalDate.of(2023, 9, 21),
            111,
            "불철주야 달리고 구르지만 실적은 바닥, 급기야 해체 위기를 맞는 마약반! 더 이상 물러설 곳이 없는 팀의 맏형 고반장은 국제 범죄조직의 국내 마약 밀반입 정황을 포착하고 장형사, 마형사, 영호, 재훈까지 4명의 팀원들과 함께 잠복 수사에 나선다. " +
                "마약반은 24시간 감시를 위해 범죄조직의 아지트 앞 치킨집을 인수해 위장 창업을 하게 되고, 뜻밖의 절대미각을 지닌 마형사의 숨은 재능으로 치킨집은 일약 맛집으로 입소문이 나기 시작한다. 수사는 뒷전, 치킨장사로 눈코 뜰 새 없이 바빠진 마약반에게 어느 날 절호의 기회가 찾아오는데… 범인을 잡을 것인가, 닭을 잡을 것인가!",
            listOf(
                TheaterModel("아주아주아주아주아주 긴 이름", listOf(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(16, 0), LocalTime.of(18, 0))),
            )
        ),
        MovieModel(
            R.drawable.harry_potter_and_the_sorcerers_stone,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            listOf(
                TheaterModel("신촌", listOf(LocalTime.of(19, 0), LocalTime.of(21, 0), LocalTime.of(23, 0))),
                TheaterModel("아현", listOf(LocalTime.of(10, 0), LocalTime.of(20, 0), LocalTime.of(22, 0))),
            )
        ),
        MovieModel(
            R.drawable.fifty_shades_freed,
            "Fifty Shades Freed",
            LocalDate.of(2023, 11, 11),
            LocalDate.of(2023, 11, 19),
            105,
            "모든 과거를 잊고 서로에게 더 깊게 빠져든 ‘크리스찬 그레이’와 ‘아나스타샤’. 그레이의 독특한 취향으로 시작된 이 비밀스러운 관계는 더 큰 자극을 원하는 아나스타샤로 인해 역전되고, 마침내 그녀의 본능이 지배하는 마지막 절정의 순간을 맞이하게 되는데…",
            listOf(
                TheaterModel("충정로", listOf(LocalTime.of(19, 0), LocalTime.of(21, 0), LocalTime.of(23, 0))),
                TheaterModel("선릉", listOf(LocalTime.of(10, 0), LocalTime.of(20, 0), LocalTime.of(22, 0))),
            )
        )
    )

    fun getAll(): List<MovieModel> = items.toList()
}
