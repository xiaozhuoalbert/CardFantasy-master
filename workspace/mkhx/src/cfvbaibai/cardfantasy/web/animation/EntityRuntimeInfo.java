package cfvbaibai.cardfantasy.web.animation;

import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.EntityInfo;
import cfvbaibai.cardfantasy.engine.LegionInfo;
import cfvbaibai.cardfantasy.engine.RuneInfo;

public class EntityRuntimeInfo {
    private String ownerId;
    private String type;
    private String uniqueName;
    
    public EntityRuntimeInfo(EntityInfo entityInfo) {
        this.ownerId = entityInfo.getOwner().getId();
        if (entityInfo instanceof CardInfo) {
            this.type = "Card";
            this.uniqueName = ((CardInfo)entityInfo).getUniqueName();
        } else if (entityInfo instanceof RuneInfo) {
            this.type = "Rune";
            this.uniqueName = ((RuneInfo)entityInfo).getName();
        } else if (entityInfo instanceof LegionInfo) {
            this.type = "Legion";
            this.uniqueName = "军团";
        }
    }
    
    public String getOwnerId() {
        return this.ownerId;
    }

    public String getType() {
        return type;
    }

    public String getUniqueName() {
        return uniqueName;
    }
}
