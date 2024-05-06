package com.travel.withaeng.domain.destination

import com.travel.withaeng.common.cd.CityCd
import com.travel.withaeng.common.cd.ContinentCd
import com.travel.withaeng.common.cd.CountryCd
import org.springframework.stereotype.Service

@Service
class DestinationService {

    fun getDestinationList() : DestinationDto{

        val continentList = mutableListOf<Continent>()

        for (continentCd in ContinentCd.entries) {

            // Create a continent object
            val continent = Continent(continentCd.continentCd, continentCd.continentNm)
            val countryList = mutableListOf<Country>()

            for(countryCd in CountryCd.entries){
                if(continentCd.continentCd.equals(countryCd.continentCd)){
                    val country = Country(countryCd.countryCd, countryCd.countryNm)

                    val cityList = mutableListOf<City>()
                    for(cityCd in CityCd.entries){
                        if(countryCd.countryCd.equals(cityCd.countryCd)){
                            val city = City(cityCd.cityCd, cityCd.cityNm)
                            cityList.add(city)
                        }
                    }
                    country.cityList = cityList
                    countryList.add(country)
                }
            }

            continent.countryList = countryList;
            // Add the continent to the list
            continentList.add(continent)
        }

        // Create and return DestinationDto
        return DestinationDto(continentList)
    }
}