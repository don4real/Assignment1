

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tf.idf.DocumentRank;
import tf.idf.Term;

public class SearchEngine_Main {

	private JFrame frame;
	private JTextField SearchText_JTextField;
	private JLabel results_JLabel;
	private ArrayList<String> documentNames;
	private TreeMap<String, Term> finalMap;
	private TokenCreator tokenCreator;
	private QueryHandeler queryHandeler;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEngine_Main window = new SearchEngine_Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private SearchEngine_Main() {
		initialize_domain();
		initialize_gui();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize_gui() {
		frame = new JFrame("Search Engine");
		frame.setSize(600, 400);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Title_JLabel = new JLabel("Search Engine");
		Title_JLabel.setLocation(115, 0);
		Title_JLabel.setSize(245, 50);
		Title_JLabel.setFont(new Font("Serif", Font.PLAIN, 28));
		Title_JLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(Title_JLabel);
		
		SearchText_JTextField = new JTextField("");
		SearchText_JTextField.setBounds(56, 47, 350, 25);
		SearchText_JTextField.setColumns(10);
		frame.getContentPane().add(SearchText_JTextField);
		
		JButton Search_JButton = new JButton("Search");
		Search_JButton.setSize(100, 25);
		Search_JButton.setLocation(179, 84);
		
		frame.getContentPane().add(Search_JButton);
		
		results_JLabel = new JLabel();
		JScrollPane ResultsPane_JScrollPane = new JScrollPane(results_JLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ResultsPane_JScrollPane.setBounds(12, 114, 420, 146);
		frame.getContentPane().add(ResultsPane_JScrollPane);
		
		Search_JButton.addActionListener(search());
		
		
		
	}
	private void initialize_domain(){
		documentNames = DocumentCollector.getDocNames();
		String path = DocumentCollector.getDocumentPath();
		tokenCreator = new TokenCreator();		
		finalMap = tokenCreator.tokenize(path, documentNames);
		queryHandeler = new QueryHandeler(finalMap, tokenCreator);
	}
	
	private ActionListener search(){
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = SearchText_JTextField.getText();
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
