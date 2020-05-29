package co.mz.osoma.editor.modelo;

import java.util.List;

public interface NodeObject {
    public NodeObject getItem();
    public List<NodeObject> hasChild();
}
