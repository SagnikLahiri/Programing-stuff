import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Main extends JFrame {
	

		
		 public Main() {

		       add(new Game());
		        
		        setResizable(false);
		        pack();
		   /*    try {
					
		    	   setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/Siddhartha/workspace/Java_project/image/sky.png")))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
				System.out.println("lalala");	
					e.printStackTrace();
				}*/
		     	   
		        setVisible(false);
		        setTitle("WORM");
		        setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }
		 
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			 EventQueue.invokeLater(new Runnable() {
		            @Override
		            public void run() {                
		                JFrame jf = new Main();
		                jf.setVisible(true);    
		               
		            }
		        });
		    }
		}

