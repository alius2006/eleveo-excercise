package dto

import helperMethods.Dates

class ConnectionDto {
    public String From, To
    public int Day

    static ConnectionDto getNextMondayDto() {
        def connection = new ConnectionDto()
        connection.From = "Ostrava"
        connection.To = "Brno"
        connection.Day = Dates.getNextMonday().dayOfMonth
        return connection
    }
}
