import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Count {
	public static int count = 0;
	int i = 0;
	public Count() {
		count ++;
		i++;
	}
	public static void main(String[] args) {
	/*	Count c1 = new Count();
		Count c2 = new Count();
		String s = "lg cns vcc phase";
		System.out.println(Count.count + c1.i + c2.i);
		System.out.println(s.indexOf("p"));
		StringTokenizer st = new StringTokenizer(s, " ");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		System.out.println(Math.ceil(3.14));
		System.out.println(Math.floor(1.445));
		System.out.println(Math.round(5.63));
		*/
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(2, "4");
		list.set(0, "5");
		list.remove(1);
		list.remove("6");
		int n = list.size();
		for(int cnt = 0; cnt < n ; cnt++) {
			String str = list.get(cnt);
			System.out.println(str);
		}
		
	}
}
