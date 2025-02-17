package data;

public enum StandardOfLiving {
    ULTRA_HIGH,
    HIGH,
    VERY_LOW;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var government : values()) {
            nameList.append(government.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
