package lat.jack.employee.employee;

import lat.jack.employee.employee.DataStructures.TreeNode;
import org.junit.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TreeNodeTest {

    @Test
    public void testTreeNode() {
        TreeNode<String> root = new TreeNode<>("root");

        // Test getData
        assertEquals("root", root.getData());

        // Test addChild to check if getChildren returns the correct values
        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");
        root.addChild(child1);
        root.addChild(child2);

        List<TreeNode> children = root.getChildren();
        assertEquals(2, children.size());
        assertEquals("child1", children.get(0).getData());
        assertEquals("child2", children.get(1).getData());
    }
}
