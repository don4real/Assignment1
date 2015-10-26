import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tf.idf.DocumentRank;
import tf.idf.Term;


public class Gui extends JFrame {
	private static final long serialVersionUID = -1049958103353244632L;
	JButton search_JButton;
	JTextField searchBar_JTextField;
	JLabel title_JLabel;
	JLabel results_JLabel;
	QueryHandeler queryHandeler;
	public Gui(TreeMap<String, Term> finalMap, TokenCreator tokenCreator){
		super("Search Engine");
		setSize(600,400);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		initialize();
		queryHandeler = new QueryHandeler(finalMap,tokenCreator);
	}
	private void initialize() {
		// TODO Auto-generated method stub
		setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		title_JLabel =  new JLabel("Search Engine");
		title_JLabel.setFont(new Font("Serif", Font.PLAIN, 28));
		title_JLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		searchBar_JTextField = new JTextField();
		searchBar_JTextField.setMaximumSize(new Dimension(400,20));
		
		search_JButton = new JButton("Search");
		search_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		results_JLabel =new JLabel(" ");
		results_JLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		results_JLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(title_JLabel);
		add(searchBar_JTextField);
		add(search_JButton);
		add(results_JLabel);
		
		//Actions
		search_JButton.addActionListener(search());
		repaint();
	}
	private ActionListener search(){
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = searchBar_JTextField.getText();
				ArrayList<DocumentRank> queryResults = queryHandeler.getQueryResults(query);			
				results_JLabel.setText("<html> <table><th>id</th><th>Document Name</th><th>Rank</th>");
				for(int i=0;i<queryResults.size();i++){
					results_JLabel.setText(results_JLabel.getText() + " <tr>"+ "<td>" + queryResults.get(i).getId() + "</td> <td>"  + queryResults.get(i).getName() + "</td> <td>"  + queryResults.get(i).getRank() + "</td> </tr>"  );
				}
				results_JLabel.setText(results_JLabel.getText() + "</table></html>");
				
			}
		};
		
	}
}
 