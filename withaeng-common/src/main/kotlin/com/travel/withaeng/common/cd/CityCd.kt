package com.travel.withaeng.common.cd

enum class CityCd (

    val countryCd: String,
    val cityCd : String,
    val cityNm : String

){

    SEOUL(CountryCd.KOREA.countryCd, "SEOUL", "서울"),
    BUSAN(CountryCd.KOREA.countryCd, "BUSAN", "부산"),
    INCHEON(CountryCd.KOREA.countryCd, "INCHEON", "인천"),
    DAEGU(CountryCd.KOREA.countryCd, "DAEGU", "대구"),
    GWANGJU(CountryCd.KOREA.countryCd, "GWANGJU", "광주"),
    DAEJEON(CountryCd.KOREA.countryCd, "DAEJEON", "대전"),
    ULSAN(CountryCd.KOREA.countryCd, "ULSAN", "울산"),
    SUWON(CountryCd.KOREA.countryCd, "SUWON", "수원"),
    CHANGWON(CountryCd.KOREA.countryCd, "CHANGWON", "창원"),
    JEJU(CountryCd.KOREA.countryCd, "JEJU", "제주"),

    // Japan
    TOKYO(CountryCd.JAPAN.countryCd, "TOKYO", "도쿄"),
    OSAKA(CountryCd.JAPAN.countryCd, "OSAKA", "오사카"),
    KYOTO(CountryCd.JAPAN.countryCd, "KYOTO", "교토"),
    SAPPORO(CountryCd.JAPAN.countryCd, "SAPPORO", "삿포로"),
    FUKUOKA(CountryCd.JAPAN.countryCd, "FUKUOKA", "후쿠오카"),
    HIROSHIMA(CountryCd.JAPAN.countryCd, "HIROSHIMA", "히로시마"),
    NAGOYA(CountryCd.JAPAN.countryCd, "NAGOYA", "나고야"),
    OKINAWA(CountryCd.JAPAN.countryCd, "OKINAWA", "오키나와"),
    KANAZAWA(CountryCd.JAPAN.countryCd, "KANAZAWA", "가나자와"),

    // China
    BEIJING(CountryCd.CHINA.countryCd, "BEIJING", "베이징"),
    SHANGHAI(CountryCd.CHINA.countryCd, "SHANGHAI", "상하이"),
    HONG_KONG(CountryCd.CHINA.countryCd, "HONG_KONG", "홍콩"),
    MACAO(CountryCd.CHINA.countryCd, "MACAO", "마카오"),
    GUANGZHOU(CountryCd.CHINA.countryCd, "GUANGZHOU", "광저우"),
    SHENZHEN(CountryCd.CHINA.countryCd, "SHENZHEN", "선전"),
    CHENGDU(CountryCd.CHINA.countryCd, "CHENGDU", "청두"),
    XIAMEN(CountryCd.CHINA.countryCd, "XIAMEN", "샤먼"),
    TIANJIN(CountryCd.CHINA.countryCd, "TIANJIN", "천진"),

    // United States
    NEW_YORK(CountryCd.UNITED_STATES.countryCd, "NEW_YORK", "뉴욕"),
    LOS_ANGELES(CountryCd.UNITED_STATES.countryCd, "LOS_ANGELES", "로스앤젤레스"),
    CHICAGO(CountryCd.UNITED_STATES.countryCd, "CHICAGO", "시카고"),
    LAS_VEGAS(CountryCd.UNITED_STATES.countryCd, "LAS_VEGAS", "라스베이거스"),
    SAN_FRANCISCO(CountryCd.UNITED_STATES.countryCd, "SAN_FRANCISCO", "샌프란시스코"),
    WASHINGTON_DC(CountryCd.UNITED_STATES.countryCd, "WASHINGTON_DC", "워싱턴 D.C."),
    MIAMI(CountryCd.UNITED_STATES.countryCd, "MIAMI", "마이애미"),
    ORLANDO(CountryCd.UNITED_STATES.countryCd, "ORLANDO", "올랜도"),
    SEATTLE(CountryCd.UNITED_STATES.countryCd, "SEATTLE", "시애틀"),

    // United Kingdom
    LONDON(CountryCd.ENGLAND.countryCd, "LONDON", "런던"),
    EDINBURGH(CountryCd.ENGLAND.countryCd, "EDINBURGH", "에든버러"),
    MANCHESTER(CountryCd.ENGLAND.countryCd, "MANCHESTER", "맨체스터"),
    BIRMINGHAM(CountryCd.ENGLAND.countryCd, "BIRMINGHAM", "버밍엄"),
    GLASGOW(CountryCd.ENGLAND.countryCd, "GLASGOW", "글래스고"),
    LIVERPOOL(CountryCd.ENGLAND.countryCd, "LIVERPOOL", "리버풀"),
    CAMBRIDGE(CountryCd.ENGLAND.countryCd, "CAMBRIDGE", "케임브리지"),

    // France
    PARIS(CountryCd.FRANCE.countryCd, "PARIS", "파리"),
    MARSEILLE(CountryCd.FRANCE.countryCd, "MARSEILLE", "마르세유"),
    LYON(CountryCd.FRANCE.countryCd, "LYON", "리옹"),
    NICE(CountryCd.FRANCE.countryCd, "NICE", "니스"),
    BORDEAUX(CountryCd.FRANCE.countryCd, "BORDEAUX", "보르도"),
    STRASBOURG(CountryCd.FRANCE.countryCd, "STRASBOURG", "스트라스부르"),

    // Germany
    BERLIN(CountryCd.GERMANY.countryCd, "BERLIN", "베를린"),
    MUNICH(CountryCd.GERMANY.countryCd, "MUNICH", "뮌헨"),
    FRANKFURT(CountryCd.GERMANY.countryCd, "FRANKFURT", "프랑크푸르트"),
    HAMBURG(CountryCd.GERMANY.countryCd, "HAMBURG", "함부르크"),
    COLOGNE(CountryCd.GERMANY.countryCd, "COLOGNE", "쾰른"),
    DUSSELDORF(CountryCd.GERMANY.countryCd, "DUSSELDORF", "뒤셀도르프"),

    // Italy
    ROME(CountryCd.ITALY.countryCd, "ROME", "로마"),
    MILAN(CountryCd.ITALY.countryCd, "MILAN", "밀라노"),
    VENICE(CountryCd.ITALY.countryCd, "VENICE", "베네치아"),
    FLORENCE(CountryCd.ITALY.countryCd, "FLORENCE", "피렌체"),
    NAPLES(CountryCd.ITALY.countryCd, "NAPLES", "나폴리"),
    TURIN(CountryCd.ITALY.countryCd, "TURIN", "투린"),

    // Spain
    MADRID(CountryCd.SPAIN.countryCd, "MADRID", "마드리드"),
    BARCELONA(CountryCd.SPAIN.countryCd, "BARCELONA", "바르셀로나"),
    VALENCIA(CountryCd.SPAIN.countryCd, "VALENCIA", "발렌시아"),
    SEVILLE(CountryCd.SPAIN.countryCd, "SEVILLE", "세비야"),
    MALAGA(CountryCd.SPAIN.countryCd, "MALAGA", "말라가"),
    BILBAO(CountryCd.SPAIN.countryCd, "BILBAO", "빌바오"),

    // Canada
    TORONTO(CountryCd.CANADA.countryCd, "TORONTO", "토론토"),
    VANCOUVER(CountryCd.CANADA.countryCd, "VANCOUVER", "밴쿠버"),
    MONTREAL(CountryCd.CANADA.countryCd, "MONTREAL", "몬트리올"),
    CALGARY(CountryCd.CANADA.countryCd, "CALGARY", "캘거리"),
    OTTAWA(CountryCd.CANADA.countryCd, "OTTAWA", "오타와"),
    QUEBEC_CITY(CountryCd.CANADA.countryCd, "QUEBEC_CITY", "퀘벡 시티"),

    // Australia
    SYDNEY(CountryCd.AUSTRALIA.countryCd, "SYDNEY", "시드니"),
    MELBOURNE(CountryCd.AUSTRALIA.countryCd, "MELBOURNE", "멜버른"),
    BRISBANE(CountryCd.AUSTRALIA.countryCd, "BRISBANE", "브리즈번"),
    PERTH(CountryCd.AUSTRALIA.countryCd, "PERTH", "퍼스"),
    ADELAIDE(CountryCd.AUSTRALIA.countryCd, "ADELAIDE", "애들레이드"),
    CANBERRA(CountryCd.AUSTRALIA.countryCd, "CANBERRA", "캔버라"),

    // Brazil
    SAO_PAULO(CountryCd.BRAZIL.countryCd, "SAO_PAULO", "상파울로"),
    RIO_DE_JANEIRO(CountryCd.BRAZIL.countryCd, "RIO_DE_JANEIRO", "리오 데 자네이로"),
    BRASILIA(CountryCd.BRAZIL.countryCd, "BRASILIA", "브라질리아"),
    SALVADOR(CountryCd.BRAZIL.countryCd, "SALVADOR", "살바도르"),
    BELO_HORIZONTE(CountryCd.BRAZIL.countryCd, "BELO_HORIZONTE", "벨루 오리손티"),

    // India
    MUMBAI(CountryCd.INDIA.countryCd, "MUMBAI", "뭄바이"),
    NEW_DELHI(CountryCd.INDIA.countryCd, "NEW_DELHI", "뉴델리"),
    BANGALORE(CountryCd.INDIA.countryCd, "BANGALORE", "방갈로르"),
    CHENNAI(CountryCd.INDIA.countryCd, "CHENNAI", "첸나이"),
    KOLKATA(CountryCd.INDIA.countryCd, "KOLKATA", "콜카타"),
    HYDERABAD(CountryCd.INDIA.countryCd, "HYDERABAD", "하이데라바드"),

    MOSCOW(CountryCd.RUSSIA.countryCd, "MOSCOW", "모스크바"),
    SAINT_PETERSBURG(CountryCd.RUSSIA.countryCd, "SAINT_PETERSBURG", "상트페테르부르크"),
    NOVOSIBIRSK(CountryCd.RUSSIA.countryCd, "NOVOSIBIRSK", "노보시비르스크"),
    EKATERINBURG(CountryCd.RUSSIA.countryCd, "EKATERINBURG", "예카테린부르크"),
    KAZAN(CountryCd.RUSSIA.countryCd, "KAZAN", "카잔"),
    NIZHNY_NOVGOROD(CountryCd.RUSSIA.countryCd, "NIZHNY_NOVGOROD", "니즈니 노브고로드"),

    // Turkey
    ISTANBUL(CountryCd.TURKIYE.countryCd, "ISTANBUL", "이스탄불"),
    ANKARA(CountryCd.TURKIYE.countryCd, "ANKARA", "앙카라"),
    IZMIR(CountryCd.TURKIYE.countryCd, "IZMIR", "이즈미르"),
    BURSA(CountryCd.TURKIYE.countryCd, "BURSA", "부르사"),
    ANTALYA(CountryCd.TURKIYE.countryCd, "ANTALYA", "안탈리아"),
    ADANA(CountryCd.TURKIYE.countryCd, "ADANA", "아다나"),

    // Argentina
    BUENOS_AIRES(CountryCd.ARGENTINA.countryCd, "BUENOS_AIRES", "부에노스 아이레스"),
    CORDOBA(CountryCd.ARGENTINA.countryCd, "CORDOBA", "코르도바"),
    ROSARIO(CountryCd.ARGENTINA.countryCd, "ROSARIO", "로사리오"),
    MENDOZA(CountryCd.ARGENTINA.countryCd, "MENDOZA", "멘도사"),
    SALTA(CountryCd.ARGENTINA.countryCd, "SALTA", "살타"),

    // Egypt
    CAIRO(CountryCd.EGYPT.countryCd, "CAIRO", "카이로"),
    ALEXANDRIA(CountryCd.EGYPT.countryCd, "ALEXANDRIA", "알렉산드리아"),
    LUXOR(CountryCd.EGYPT.countryCd, "LUXOR", "룩소르"),
    SHARM_EL_SHEIKH(CountryCd.EGYPT.countryCd, "SHARM_EL_SHEIKH", "샤름 엘 쉬크"),
    ASWAN(CountryCd.EGYPT.countryCd, "ASWAN", "아스완"),
    HURGHADA(CountryCd.EGYPT.countryCd, "HURGHADA", "후르가다"),

    // Thailand
    BANGKOK(CountryCd.THAILAND.countryCd, "BANGKOK", "방콕"),
    PHUKET(CountryCd.THAILAND.countryCd, "PHUKET", "푸켓"),
    CHIANG_MAI(CountryCd.THAILAND.countryCd, "CHIANG_MAI", "치앙마이"),
    PATTAYA(CountryCd.THAILAND.countryCd, "PATTAYA", "파타야"),
    KRABI(CountryCd.THAILAND.countryCd, "KRABI", "크라비"),
    HUA_HIN(CountryCd.THAILAND.countryCd, "HUA_HIN", "후아힌"),

    // Mexico
    MEXICO_CITY(CountryCd.MEXICO.countryCd, "MEXICO_CITY", "멕시코시티"),
    CANCUN(CountryCd.MEXICO.countryCd, "CANCUN", "칸쿤"),
    GUADALAJARA(CountryCd.MEXICO.countryCd, "GUADALAJARA", "과달라하라"),
    MONTERREY(CountryCd.MEXICO.countryCd, "MONTERREY", "몬테레이"),
    TULUM(CountryCd.MEXICO.countryCd, "TULUM", "툴룸"),
    OAXACA(CountryCd.MEXICO.countryCd, "OAXACA", "오아하카"),

    // Indonesia
    JAKARTA(CountryCd.INDONESIA.countryCd, "JAKARTA", "자카르타"),
    BALI(CountryCd.INDONESIA.countryCd, "BALI", "발리"),
    BANDUNG(CountryCd.INDONESIA.countryCd, "BANDUNG", "반둥"),
    YOGYAKARTA(CountryCd.INDONESIA.countryCd, "YOGYAKARTA", "요가야카르타"),
    SURABAYA(CountryCd.INDONESIA.countryCd, "SURABAYA", "수라바야"),
    MAKASSAR(CountryCd.INDONESIA.countryCd, "MAKASSAR", "마카사르");

}