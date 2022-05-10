package flightBooking.entity;

public enum Airline {

    AIR_ASTANA("Air Astana"),
    AIR_BALTIC("Air Baltic"),
    AIR_EUROPA("Air Europa"),
    AIR_FRANCE("Air France"),
    AIR_ICELAND_CONNECT("Air Iceland Connect"),
    AIR_MALTA("Air Malta"),
    AIR_SERBIA("Air Serbia"),
    ALITALIA("Alitalia"),
    AUSTRIAN_AIRLINES("Austrian Airlines"),
    AZUR_AIR("Azur Air"),
    AZYMUTH("Azymuth"),
    BELAVIA("Belavia"),
    BLUE_AIR("Blue Air"),
    BRITISH_AIRWAYS("British Airways"),
    BRUSSELS_AIRLINES("Brussels Airlines"),
    BULGARIA_AIR("Bulgaria Air"),
    CROATIAN_AIRLINES("Croatian Airlines"),
    CZECH_AIRLINES("Czech Airlines"),
    EASYJET_EUROPE("EasyJet Europe"),
    EASYJET_SWITZERLAND("EasyJet Switzerland"),
    EASYJET_UK("EasyJet UK"),
    ELLINAIR("Ellinair"),
    ENTER_AIR("Enter Air"),
    EUROWINGS("Eurowings"),
    FINNAIR("Finnair"),
    IBERIA("Iberia"),
    ICELANDAIR("Icelandair"),
    I_FLY("I-Fly"),
    KLM("KLM"),
    LEVEL("Level"),
    LOT_POLISH_AIRLINES("LOT Polish Airlines"),
    LUFTHANSA("Lufthansa"),
    LUXAIR("Luxair"),
    NORDSTAR("Nordstar"),
    NORDWIND_AIRLINES("Nordwind Airlines"),
    NORWEGIAN_AIR_SHUTTLE_ASA("Norwegian Air Shuttle ASA"),
    OLYMPIC_AIR("Olympic Air"),
    PEGAS_FLY("Pegas Fly"),
    PEGASUS_AIRLINES("Pegasus Airlines"),
    REDWINGS_AIRLINES("RedWings Airlines"),
    ROYAL_FLIGHT("Royal Flight"),
    RYANAIR("Ryanair"),
    S7_AIRLINES("S7 Airlines"),
    SCANDINAVIAN_AIRLINES("Scandinavian Airlines"),
    SKY_EXPRESS("Sky Express"),
    SKY_UP("Sky Up"),
    SMARTAVIA("Smartavia"),
    SMARTWINGS("SmartWings"),
    SWISS_INTERNATIONAL_AIRLINES("Swiss International AirLines"),
    TAP_AIR_PORTUGAL("TAP Air Portugal"),
    TAROM("TAROM"),
    TRANSAVIA("Transavia"),
    TUI_AIRWAYS("TUI Airways"),
    TURKISH_AIRLINES("Turkish Airlines"),
    UKRAINE_INTERNATIONAL_AIRLINES("Ukraine International Airlines"),
    URAL_AIRLINES("Ural Airlines"),
    UTAIR_AVIATION("Utair Aviation"),
    VIRGINATLANTIC(" VirginAtlantic"),
    VOLOTEA("Volotea"),
    VUELING_AIRLINES("Vueling Airlines"),
    WINDROSE("Windrose"),
    WIZZ_AIR("Wizz Air");

    private final String displayEnum;

    Airline(String displayEnum) {
        this.displayEnum = displayEnum;
    }

    public String getDisplayEnum() {
        return displayEnum;
    }


}
