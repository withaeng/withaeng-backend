package com.travel.withaeng.domain.destination


import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Setter
@Getter
class DestinationDto (

    var continentList : List<Continent>
)

@Setter
@Getter
@NoArgsConstructor
class Continent(

    var continentCd : String,
    var continentNm : String,
    var countryList : List<Country>

){
    constructor(continentCd : String, continentNm : String) :
            this(continentCd, continentNm, mutableListOf())

}

@Setter
@Getter
@NoArgsConstructor
class Country(

    var countryCd : String,
    var countryNm : String,
    var cityList : List<City>
){

    constructor(countryCd : String, countryNm : String) :
            this(countryCd, countryNm, mutableListOf())


}

@Setter
@Getter
class City(

    var cityCd : String,
    var cityNm : String,
)
