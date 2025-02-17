package data;

public enum Government {
    DESPOTISM,
    DIARCHY,
    TECHNOCRACY;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var government : values()) {
            nameList.append(government.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
