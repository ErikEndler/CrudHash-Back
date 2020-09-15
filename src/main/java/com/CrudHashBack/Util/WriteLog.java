package com.CrudHashBack.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteLog {
	
	public void writer(String string, String acao) {
		try {
			System.out.println("Entrou no try comn :"+ string);

			FileWriter arq = new FileWriter("log.txt", true);
			PrintWriter writerArq = new PrintWriter(arq);
			writerArq.println(data()+" - "+acao+" - "+string);
			
			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String data() {
		Date hoje = new Date();
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(hoje);
	}
	

}
