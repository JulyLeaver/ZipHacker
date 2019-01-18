import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;

public class Main {

	public static void main(String[] args) {
		(new Main()).run();
	}

	final String[] passwd = new String[] { 
			"qwer", "asdf", "1234" 
			};

	void run() {
		cp(new boolean[passwd.length], new ArrayList<String>());
	}

	void cp(boolean[] visi, ArrayList<String> p) {
		if (unZip(al2s(p))) {
			System.out.println("password: " + al2s(p));
			System.exit(0);
		}
		for (int i = 0; i < passwd.length; ++i) {
			if (!visi[i]) {
				visi[i] = true;
				p.add(passwd[i]);
				cp(visi, p);
				visi[i] = false;
				p.remove(p.size() - 1);
			}
		}
	}

	String al2s(ArrayList<String> al) {
		String ret = "";
		for (int i = 0; i < al.size(); ++i) {
			ret += al.get(i);
		}
		return ret;
	}

	boolean unZip(final String passwd) {
		/*
		 * System.out.println(passwd);
		 */
		try {
			ZipFile zf = new ZipFile("path");
			/*
			 * if(zf.isEncrypted()) { zf.setPassword(passwd); }
			 */
			zf.setPassword(passwd);
			zf.extractAll("path");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
