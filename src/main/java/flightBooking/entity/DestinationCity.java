package flightBooking.entity;

public enum DestinationCity {

    AMSTERDAM_NETHERLANDS("Amsterdam, Netherlands"),
    ATHENS_GREECE("Athens, Greece"),
    BERLIN_GERMANY("Berlin, Germany"),
    BERN_SWITZERLAND("Bern, Switzerland"),
    BRATISLAVA_SLOVAKIA("Bratislava, Slovakia"),
    BRUSSELS_BELGIUM("Brussels, Belgium"),
    BUCHAREST_ROMANIA("Bucharest, Romania"),
    BUDAPEST_HUNGARY("Budapest, Hungary"),
    CHISINAU_MOLDOVA("Chisinau, Moldova"),
    COPENHAGEN_DENMARK("Copenhagen, Denmark"),
    DUBLIN_IRELAND("Dublin, Ireland"),
    HELSINKI_FINLAND("Helsinki, Finland"),
    KIEV_UKRAINE("Kiev, Ukraine"),
    LISBON_PORTUGAL("Lisbon, Portugal"),
    LJUBLJANA_SLOVENIA("Ljubljana, Slovenia"),
    LONDON_UNITED_KINGDOM("London, United Kingdom"),
    LUXEMBOURG_LUXEMBOURG("Luxembourg, Luxembourg"),
    MADRID_SPAIN("Madrid, Spain"),
    MINSK_BELARUS("Minsk, Belarus"),
    MONACO_MONACO("Monaco, Monaco"),
    OSLO_NORWAY("Oslo, Norway"),
    PARIS_FRANCE("Paris, France"),
    PODGORICA_MONTENEGRO("Podgorica, Montenegro"),
    PRAGUE_CZECHIA("Prague, Czechia"),
    REYKJAVIK_ICELAND("Reykjavik, Iceland"),
    ROME_ITALY("Rome, Italy"),
    SARAJEVO_BOSNIA_AND_HERZEGOVINA("Sarajevo, Bosnia and Herzegovina"),
    SERBIA_BELGRADE("Serbia, Belgrade"),
    SKOPJE_NORTH_MACEDONIA("Skopje, North Macedonia"),
    SOFIA_BULGARIA("Sofia, Bulgaria"),
    STOCKHOLM_SWEDEN("Stockholm, Sweden"),
    TALLINN_ESTONIA("Tallinn, Estonia"),
    TIMISOARA_ROMANIA("Timisoara, Romania"),
    VIENNA_AUSTRIA("Vienna, Austria"),
    WARSAW_POLAND("Warsaw, Poland"),
    ZAGREB_CROATIA("Zagreb, Croatia");


    private final String displayEnum;

    DestinationCity(String displayEnum) {
        this.displayEnum = displayEnum;
    }

    public String getDisplayEnum() {
        return displayEnum;
    }



}
