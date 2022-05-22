 
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception; 
import java.util.Timer;
import java.util.TimerTask; 
  
  																	//Create a class called loginfunction which is used to get authenticate

//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
class loginfunction extends JFrame implements ActionListener  
{  
    //initialize button, panel, label, and text field  
    JButton button;  
    JPanel newPanel;  
    JLabel userLabel, passLabel;  
    JTextField  usertextfield, textField2;  
      
    //calling constructor  
    loginfunction()  
    {     
          
        //create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Enter username here");      //set label value for textField1  
          
        //create text field to get username from the user  
        usertextfield = new JTextField(15);    //set length of the text  
  
        //create label for password  
        passLabel = new JLabel();  
        passLabel.setText("Enter Password");      //set label value for textField2  
          
        //create text field to get password from the user  
        textField2 = new JPasswordField(15);    //set length for the password  
          
        //create submit button  
        button = new JButton("SUBMIT"); //set label to button  
       // newpanel.setBounds(20,20,450,20);
        //create panel to put form elements  
        newPanel = new JPanel(new GridLayout(3, 1));  
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(usertextfield);   //set text field to panel  
        newPanel.add(passLabel);    //set password label to panel  
        newPanel.add(textField2);   //set text field to panel  
        newPanel.add(button);           //set button to panel  
         
        
        add(newPanel);  
          
        //perform action on button click   
        button.addActionListener(this);     //add action listener to button  
        setTitle("***************  Login for the Examination  *********************");         //set title to the login form  
        
    }  
      
    //define abstract method actionPerformed() which will be called on button click   
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {  
        String userValue = usertextfield.getText();        //get user entered username from the textField1  
        String passValue = textField2.getText();        //get user entered pasword from the textField2  
       
    
        if(!passValue.equals(""))
            new OnlineExam(userValue); 
        else{
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
        
}  

 

class OnlineExam extends JFrame implements ActionListener  
{  
    JLabel que;  
  JLabel timerr;
     
    
    JRadioButton radioButton[]=new JRadioButton[6];  
    JButton savebutt,revbutt;  
    ButtonGroup bg;  
    int count=0,current=0,x=1,y=1,now=0;  
    int m[]=new int[10];  
    Timer timer = new Timer();
    
   
    OnlineExam(String s)  
    {  
        
        super(s); 
      
       que=new JLabel();
       timerr = new JLabel();  
        
        add(que);
      add(timerr);  
        bg=new ButtonGroup();  
        for(int i=0;i<5;i++)  
        {  
        	radioButton[i]=new JRadioButton();     
            add(radioButton[i]);  
            bg.add(radioButton[i]);  
        }  
        savebutt=new JButton("Save and Next");  
        revbutt=new JButton("For Mark for Review");  
        savebutt.addActionListener(this);  
        revbutt.addActionListener(this);  
        add(savebutt);
        add(revbutt);  
        set();  
       que.setBounds(30,40,450,20);
       timerr.setBounds(600,20,200,10);
       radioButton[0].setBounds(50,80,100,20);  
       radioButton[1].setBounds(50,110,100,20);  
       radioButton[2].setBounds(50,140,100,20);  
       radioButton[3].setBounds(50,170,100,20);
       setTitle("*****************************  Online Examination  ***************************");
        savebutt.setBounds(95,240,140,30);  
        revbutt.setBounds(270,240,150,30);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250,200);  
        setVisible(true);  
        setSize(800,850);  
        
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 120;
    
            public void run() {
    
                timerr.setText("Timer: " + i);
                i--;
    
                if (i < 0) {
                    timer.cancel();
                    timerr.setText("              Time Over");
                }
            }
        }, 0, 1000);    
       
    }  
    public void actionPerformed(ActionEvent e)  
    {   
        
        if(e.getSource()==savebutt)  
        {  
            if(check())  
                count=count+1;  
            current++;  
            set();    
            if(current==9)  
            {  
                savebutt.setEnabled(false);  
                revbutt.setText("Result");  
            }  
        }  
        if(e.getActionCommand().equals("Marked for Review"))  
        {  
            JButton bk=new JButton("Review"+x);  
            bk.setBounds(480,20+30*x,100,30);  
            add(bk);  
            bk.addActionListener(this);  
            m[x]=current;  
            x++;  
            current++;  
            set();    
            if(current==9)  
                revbutt.setText("Result");  
            setVisible(false);  
            setVisible(true);  
        }  
        for(int i=0,y=1;i<x;i++,y++)  
        {  
        if(e.getActionCommand().equals("Review"+y))  
        {  
            if(check())  
                count=count+1;  
            now=current;  
            current=m[y];  
            set();  
            ((JButton)e.getSource()).setEnabled(false);  
            current=now;  
        }  
        }  
      
        if(e.getActionCommand().equals("Result"))  
        {  
            if(check())  
                count=count+1;  
            current++;  
            //System.out.println("correct ans="+count);  
            JOptionPane.showMessageDialog(this,"Your Score ="+count);
            if(count>5)
            JOptionPane.showMessageDialog(this,"Good Score congrats "+count);  
            if(count<5)
            	   JOptionPane.showMessageDialog(this,"you Have to improve ");  
            System.exit(0);  
        }  
    }  
    void set() {
		radioButton[4].setSelected(true);
		if (current == 0) {
			que.setText("Que1:  Which one of the following is a java keyword?");
			radioButton[0].setText("Switch");
			radioButton[1].setText("While");
			radioButton[2].setText("Public");
			radioButton[3].setText("Void");
		}
		if (current == 1) {
			que.setText("Que2:  Java declaration statement must end with");
			radioButton[0].setText("Comma");
			radioButton[1].setText("A colon");
			radioButton[2].setText("A semicolon");
			radioButton[3].setText("Full stop");
		}
		if (current == 2) {
			que.setText("Que3: Which one of the following languages is suitable to implement the OOP concepts?");
			radioButton[0].setText("Objective C and C++");
			radioButton[1].setText("Small talk");
			radioButton[2].setText("Ada");
			radioButton[3].setText("All of these");
		}
		if (current == 3) {
			que.setText("Que4: 	Java is a general purpose object oriented programming language developed by");
			radioButton[0].setText("Microsystems of U.S.A.");
			radioButton[1].setText("I.B.M.");
			radioButton[2].setText("Patrick Naughton");
			radioButton[3].setText("None of these");
		}
		if (current == 4) {
			que.setText("Que5: In an object oriented programming");
			radioButton[0].setText("Class create objects");
			radioButton[1].setText("Objects create classes");
			radioButton[2].setText("Classes use methods to communicate between them");
			radioButton[3].setText("None of these");
		}
		if (current == 5) {
			que.setText("Que6: How to read entire file in one line using java 8?");
			radioButton[0].setText("Files.readAllLines()");
			radioButton[1].setText("Files.read()");
			radioButton[2].setText("Files.readFile()");
			radioButton[3].setText("Files.lines()");
		}
		if (current == 6) {
			que.setText("Que7: Which one of the following concept is incorporated in building a java program?");
			radioButton[0].setText("Encapsulation");
			radioButton[1].setText("Inheritance");
			radioButton[2].setText("Polymorphism");
			radioButton[3].setText("All of these");
		}
		if (current == 7) {
			que.setText("Que8: The bitwise AND operator is represented by a single ampersand (&) and is surrounded on both sides by");
			radioButton[0].setText("Integer expression");
			radioButton[1].setText("Colons");
			radioButton[2].setText("Semicolons");
			radioButton[3].setText("Commas");
		}
		if (current == 8) {
			que.setText("Que9: A package is a collection of");
			radioButton[0].setText("Interfaces");
			radioButton[1].setText("Classes");
			radioButton[2].setText("Both (a) and (b)");
			radioButton[3].setText("None of these");
		}
		if (current == 9) {
			que.setText("Que10: Java does not define which type modifier?");
			radioButton[0].setText("Auto");
			radioButton[1].setText("Extern");
			radioButton[2].setText("Register");
			radioButton[3].setText("All of these");
		}
		que.setBounds(30, 40, 450, 20);
		for (int i = 0, j = 0; i <= 90; i += 30, j++)
			radioButton[j].setBounds(50, 80 + i, 200, 20);
	}

	// declare right answers.
	boolean check() {
		if (current == 0)
			return (radioButton[2].isSelected());
		if (current == 1)
			return (radioButton[2].isSelected());
		if (current == 2)
			return (radioButton[3].isSelected());
		if (current == 3)
			return (radioButton[0].isSelected());
		if (current == 4)
			return (radioButton[0].isSelected());
		if (current == 5)
			return (radioButton[0].isSelected());
		if (current == 6)
			return (radioButton[3].isSelected());
		if (current == 7)
			return (radioButton[2].isSelected());
		if (current == 8)
			return (radioButton[3].isSelected());
		if (current == 9)
			return (radioButton[3].isSelected());
		return false;
	}

    
} 



public class OnlineExamination  // Created the main class
{  
   
    public static void main(String arg[])  //This is the main method
    {  
         
        
           
            loginfunction login = new loginfunction();  
            
            login.setSize(800,200);  
            login.setLocation(250,250);  
            login.setVisible(true);  
       
       
    }  
} 