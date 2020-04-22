package engine.enums;

public enum EventType {
    HEALTH ("HealthFlask"),
    MANA ("ManaFlask");

    private String targetName;

    EventType(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetName() {
        return targetName;
    }
}
