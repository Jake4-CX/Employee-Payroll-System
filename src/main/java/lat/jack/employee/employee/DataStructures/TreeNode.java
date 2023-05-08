package lat.jack.employee.employee.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private T data;
    private List<TreeNode> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public T getData() {
        return data;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
