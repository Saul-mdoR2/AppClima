package com.example.appclima

class Ciudad(name: String, weather: ArrayList<Weather>?, main: Main) {
    var name: String = ""
    var weather: ArrayList<Weather>? = null
    var main: Main? = null

    init {
        this.name = name
        this.weather = weather
        this.main = main
    }
}

/* ESTO ES LO QUE DEVUELVE LA API
{
    "coord": {
    "lon": -99.17,
    "lat": 19.25
    },
    "weather": [
    {
        "id": 801,
        "main": "Clouds",
        "description": "algo de nubes",
        "icon": "02d"
    }
    ],
    "base": "stations",
    "main": {
    "temp": 20.45,
    "feels_like": 19.19,
    "temp_min": 20,
    "temp_max": 21.11,
    "pressure": 1029,
    "humidity": 40
    },
    "visibility": 9656,
    "wind": {
    "speed": 0.61,
    "deg": 102
    },
    "clouds": {
    "all": 20
     },
    "dt": 1608316262,
    "sys": {
    "type": 1,
    "id": 7146,
    "country": "MX",
    "sunrise": 1608296671,
    "sunset": 1608336162
    },
    "timezone": -21600,
    "id": 3527646,
    "name": "Mexico City",
    "cod": 200
} */