package cfvbaibai.cardfantasy.engine;

import cfvbaibai.cardfantasy.NonSerializable;


public class FeatureEffect {
    private FeatureEffectType type;
    @NonSerializable
    private FeatureInfo cause;
    private int value;
    private boolean eternal;
    
    public FeatureEffectType getType() {
        return type;
    }
    public void setType(FeatureEffectType type) {
        this.type = type;
    }
    public FeatureInfo getCause() {
        return cause;
    }
    public void setCause(FeatureInfo cause) {
        this.cause = cause;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public EntityInfo getSource() {
        return this.cause.getOwner();
    }
    public boolean isEternal() {
        return this.eternal;
    }
    public FeatureEffect(FeatureEffectType type, FeatureInfo cause, int value, boolean eternal) {
        this.type = type;
        this.cause = cause;
        this.value = value;
        this.eternal = eternal;
    }
}
