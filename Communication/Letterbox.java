package Communication;

import java.util.ArrayList;

public class Letterbox {
    private MoveAgentOrder order = null;

    public void setOrder(MoveAgentOrder order) {
        this.order = order;
    }

    public boolean hasOrder() {
        return this.order != null;
    }
}
