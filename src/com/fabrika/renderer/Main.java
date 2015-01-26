package com.fabrika.renderer;

public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				makeGui();
			}
		});

	}

	public static void makeGui() {
		RendererView mainFrame = new RendererView();
		mainFrame.setVisible(true);
	}
}
