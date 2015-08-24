package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

public class RunnerPart2 {
	/**测试脚本-2
	 * 实验任务二：利用启发式搜索，求解随机5*5拼图（24-数码问题）
	 * 注意：运行前要修改节点维数，将JigsawNode类中的dimension改为5
	 * 要求：不修改脚本内容，程序能够运行，且得出预期结果
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// 检查节点维数是否为5
		if(JigsawNode.getDimension() != 5){
			System.out.print("节点维数不正确，请将JigsawNode类的维数dimension改为5");
			return;
		}
		
		// 生成目标状态对象destNode: {25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};
		JigsawNode destNode = new JigsawNode(new int[]{25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0});  		

		// 生成随机初始状态对象startNode：将目标状态打散，生成可解的随机初始状态
		JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
		//JigsawNode startNode = new JigsawNode(new int[]{5,20,10,24,17,0,2,15,16,19,9,18,3,13,4,23,5,12,1,8,22,6,7,11,21,14});
		//JigsawNode startNode = new JigsawNode(new int[] {20,9,1,20,10,12,8,4,6,7,2,3,5,14,15,18,13,23,17,19,0,22,16,11,24,21});
		//JigsawNode startNode = new JigsawNode(new int[] {19,17,15,18,6,13,3,7,5,8,9,22,1,16,14,24,21,4,23,0,10,11,2,19,12,20});

		// 生成jigsaw对象：设置初始状态节点startNode和目标状态节点destNode
		Jigsaw jigsaw = new Jigsaw(startNode, destNode);

		// 执行启发式搜索示例算法
		jigsaw.ASearch();
	}

}
