package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.*;
import com.pro.weatherservice.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Mapper
public interface WeatherDataMapper {
    WeatherDataMapper getInstance = Mappers.getMapper(WeatherDataMapper.class);

    /*WeatherData toEntity(WeatherDataDto weatherDataDto);

    WeatherDataDto toDto(WeatherData weatherData);

    Coord toCoordEntity(CoordDto coordDto);

    List<Weather> toListWeatherEntity(List<WeatherDto> weatherDtoList);

    Main toMainEntity(MainDto mainDto);

    Wind toWindEntity(Wind wind);

    Rain toRainEntity(RainDto rainDto);

    Clouds toCloudsEntity(CloudsDto cloudsDto);

    Sys toSysEntity(SysDto sysDto);*/


    default WeatherData toEntity(WeatherDataDto weatherDataDto) {
        if (weatherDataDto == null) {
            return null;
        } else {
            WeatherData weatherData = new WeatherData();
            if (weatherDataDto.getWeatherDataId() != null) {
                weatherData.setId(weatherDataDto.getWeatherDataId());
            }

            if (weatherDataDto.getId() != null) {
                weatherData.setWeatherDataId(weatherDataDto.getId());
            }

            weatherData.setCoord(this.toCoordEntity(weatherDataDto.getCoord()));
            weatherData.setWeather(this.toListWeatherEntity(weatherDataDto.getWeather()));
            weatherData.setBase(weatherDataDto.getBase());
            weatherData.setMain(this.toMainEntity(weatherDataDto.getMain()));
            weatherData.setVisibility(weatherDataDto.getVisibility());
            weatherData.setWind(this.windDtoToWind(weatherDataDto.getWind()));
            weatherData.setRain(this.toRainEntity(weatherDataDto.getRain()));
            weatherData.setClouds(this.toCloudsEntity(weatherDataDto.getClouds()));
            weatherData.setDt(weatherDataDto.getDt());
            weatherData.setSys(this.toSysEntity(weatherDataDto.getSys()));
            weatherData.setTimezone(weatherDataDto.getTimezone());
            weatherData.setName(weatherDataDto.getName());
            weatherData.setCod(weatherDataDto.getCod());
            return weatherData;
        }
    }

    default WeatherDataDto toDto(WeatherData weatherData) {
        if (weatherData == null) {
            return null;
        } else {
            WeatherDataDto weatherDataDto = new WeatherDataDto();
            if (weatherData.getId() != null) {
                weatherDataDto.setWeatherDataId(weatherData.getId());
            }

            if (weatherData.getWeatherDataId() != null) {
                weatherDataDto.setId(weatherData.getWeatherDataId());
            }

            weatherDataDto.setCoord(this.coordToCoordDto(weatherData.getCoord()));
            weatherDataDto.setWeather(this.weatherListToWeatherDtoList(weatherData.getWeather()));
            weatherDataDto.setBase(weatherData.getBase());
            weatherDataDto.setMain(this.mainToMainDto(weatherData.getMain()));
            weatherDataDto.setVisibility(weatherData.getVisibility());
            weatherDataDto.setWind(this.windToWindDto(weatherData.getWind()));
            weatherDataDto.setRain(this.rainToRainDto(weatherData.getRain()));
            weatherDataDto.setClouds(this.cloudsToCloudsDto(weatherData.getClouds()));
            weatherDataDto.setDt(weatherData.getDt());
            weatherDataDto.setSys(this.sysToSysDto(weatherData.getSys()));
            weatherDataDto.setTimezone(weatherData.getTimezone());
            weatherDataDto.setName(weatherData.getName());
            weatherDataDto.setCod(weatherData.getCod());
            return weatherDataDto;
        }
    }

    default Coord toCoordEntity(CoordDto coordDto) {
        if (coordDto == null) {
            return null;
        } else {
            Coord coord = new Coord();
            coord.setId(coordDto.getCoordId());
            coord.setLon(coordDto.getLon());
            coord.setLat(coordDto.getLat());
            return coord;
        }
    }

    default List<Weather> toListWeatherEntity(List<WeatherDto> weatherDtoList) {
        if (weatherDtoList == null) {
            return null;
        } else {
            List<Weather> list = new ArrayList(weatherDtoList.size());

            for (WeatherDto weatherDto : weatherDtoList) {
                list.add(this.weatherDtoToWeather(weatherDto));
            }

            return list;
        }
    }

    default Main toMainEntity(MainDto mainDto) {
        if (mainDto == null) {
            return null;
        } else {
            Main main = new Main();
            main.setId(mainDto.getMainId());
            main.setTemp(mainDto.getTemp());
            main.setFeelsLike(mainDto.getFeelsLike());
            main.setTempMin(mainDto.getTempMin());
            main.setTempMax(mainDto.getTempMax());
            main.setPressure(mainDto.getPressure());
            main.setHumidity(mainDto.getHumidity());
            main.setSeaLevel(mainDto.getSeaLevel());
            main.setGrndLevel(mainDto.getGrndLevel());
            return main;
        }
    }

    default Wind toWindEntity(WindDto windDto) {
        if (windDto == null) {
            return null;
        } else {
            Wind wind = new Wind();
            wind.setId(windDto.getWindId());
            wind.setSpeed(windDto.getSpeed());
            wind.setDeg(windDto.getDeg());
            wind.setGust(windDto.getGust());
            return wind;
        }
    }

    default Rain toRainEntity(RainDto rainDto) {
        if (rainDto == null) {
            return null;
        } else {
            Rain rain = new Rain();
            rain.setId(rainDto.getRainId());
            rain.setOneHour(rainDto.getOneHour());
            return rain;
        }
    }

    default Clouds toCloudsEntity(CloudsDto cloudsDto) {
        if (cloudsDto == null) {
            return null;
        } else {
            Clouds clouds = new Clouds();
            clouds.setId(cloudsDto.getCloudsId());
            clouds.setAll(cloudsDto.getAll());
            return clouds;
        }
    }

    default Sys toSysEntity(SysDto sysDto) {
        if (sysDto == null) {
            return null;
        } else {
            Sys sys = new Sys();
            if (sysDto.getSysId() != null) {
                sys.setId(sysDto.getSysId());
            }

            sys.setType(sysDto.getType());
            if (sysDto.getId() != null) {
                sys.setSysId(sysDto.getId());
            }

            sys.setCountry(sysDto.getCountry());
            sys.setSunrise(sysDto.getSunrise());
            sys.setSunset(sysDto.getSunset());
            return sys;
        }
    }

    default Wind windDtoToWind(WindDto windDto) {
        if (windDto == null) {
            return null;
        } else {
            Wind wind = new Wind();
            wind.setId(windDto.getWindId());
            wind.setSpeed(windDto.getSpeed());
            wind.setDeg(windDto.getDeg());
            wind.setGust(windDto.getGust());
            return wind;
        }
    }

    default CoordDto coordToCoordDto(Coord coord) {
        if (coord == null) {
            return null;
        } else {
            CoordDto coordDto = new CoordDto();
            coordDto.setCoordId(coord.getId());
            coordDto.setLon(coord.getLon());
            coordDto.setLat(coord.getLat());
            return coordDto;
        }
    }

    default WeatherDto weatherToWeatherDto(Weather weather) {
        if (weather == null) {
            return null;
        } else {
            WeatherDto weatherDto = new WeatherDto();
            if (weather.getId() != null) {
                weatherDto.setWeatherId(weather.getId());
            }

            if (weather.getWeatherId() != null) {
                weatherDto.setId(weather.getWeatherId());
            }

            weatherDto.setMain(weather.getMain());
            weatherDto.setDescription(weather.getDescription());
            weatherDto.setIcon(weather.getIcon());
            return weatherDto;
        }
    }

    default List<WeatherDto> weatherListToWeatherDtoList(List<Weather> list) {
        if (list == null) {
            return null;
        } else {
            List<WeatherDto> weatherDtolist = new ArrayList(list.size());

            for (Weather weather : list) {
                weatherDtolist.add(this.weatherToWeatherDto(weather));
            }

            return weatherDtolist;
        }
    }

    default MainDto mainToMainDto(Main main) {
        if (main == null) {
            return null;
        } else {
            MainDto mainDto = new MainDto();
            mainDto.setMainId(main.getId());
            mainDto.setTemp(main.getTemp());
            mainDto.setFeelsLike(main.getFeelsLike());
            mainDto.setTempMin(main.getTempMin());
            mainDto.setTempMax(main.getTempMax());
            mainDto.setPressure(main.getPressure());
            mainDto.setHumidity(main.getHumidity());
            mainDto.setSeaLevel(main.getSeaLevel());
            mainDto.setGrndLevel(main.getGrndLevel());
            return mainDto;
        }
    }

    default WindDto windToWindDto(Wind wind) {
        if (wind == null) {
            return null;
        } else {
            WindDto windDto = new WindDto();
            windDto.setWindId(wind.getId());
            windDto.setSpeed(wind.getSpeed());
            windDto.setDeg(wind.getDeg());
            windDto.setGust(wind.getGust());
            return windDto;
        }
    }

    default RainDto rainToRainDto(Rain rain) {
        if (rain == null) {
            return null;
        } else {
            RainDto rainDto = new RainDto();
            rainDto.setRainId(rain.getId());
            rainDto.setOneHour(rain.getOneHour());
            return rainDto;
        }
    }

    default CloudsDto cloudsToCloudsDto(Clouds clouds) {
        if (clouds == null) {
            return null;
        } else {
            CloudsDto cloudsDto = new CloudsDto();
            cloudsDto.setCloudsId(clouds.getId());
            cloudsDto.setAll(clouds.getAll());
            return cloudsDto;
        }
    }

    default SysDto sysToSysDto(Sys sys) {
        if (sys == null) {
            return null;
        } else {
            SysDto sysDto = new SysDto();
            if (sys.getId() != null) {
                sysDto.setSysId(sys.getId());
            }

            sysDto.setType(sys.getType());
            if (sys.getSysId() != null) {
                sysDto.setId(sys.getSysId());
            }

            sysDto.setCountry(sys.getCountry());
            sysDto.setSunrise(sys.getSunrise());
            sysDto.setSunset(sys.getSunset());
            return sysDto;
        }
    }

    default Weather weatherDtoToWeather(WeatherDto weatherDto) {
        if (weatherDto == null) {
            return null;
        } else {
            Weather weather = new Weather();
            if (weatherDto.getWeatherId() != null) {
                weather.setId(weatherDto.getWeatherId());
            }

            if (weatherDto.getId() != null) {
                weather.setWeatherId(weatherDto.getId());
            }

            weather.setMain(weatherDto.getMain());
            weather.setDescription(weatherDto.getDescription());
            weather.setIcon(weatherDto.getIcon());
            return weather;
        }
    }

}
