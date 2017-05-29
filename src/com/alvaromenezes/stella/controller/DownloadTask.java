package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.communication.HttpClient;

import javax.swing.SwingWorker;


/**
 * 
 * @author Alvaro Menezes 28/05/2017
 *
 */
public class DownloadTask extends SwingWorker<String, Object> {

	private String url;
	//private CustomDialog dlg;

	public DownloadTask(String url) {
		this.url = url;
		//this.dlg = dlg;
	}

	@Override
	protected String doInBackground() throws Exception {
		HttpClient client = new HttpClient(url,null);//) dlg);
		return client.run();
	}

	@Override
	protected void done() {

		try {
			Thread.sleep(300);
			//new ProcessResponseTask(get(), dlg).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
