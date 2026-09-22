import javax.swing.*;



public class views extends JFrame  {

        JLabel name , roll_number , gender , branch ;
        JTextField tname , troll_number ,  tbranch ;
        JRadioButton male , female ;
        JCheckBox terms;
        JButton submit , reset; 
        ButtonGroup g;

        views(){

            setSize(500, 500);
            setLayout(null);
            setTitle("Student Registration Form");

            name = new JLabel("full name");
            name.setBounds(30,30,100,30);
            add(name);

            tname = new JTextField();
            tname.setBounds(150,30,200,30);
            add(tname);

            roll_number = new JLabel("roll number");
            roll_number.setBounds(30,70,100,30);
            add(roll_number);

            troll_number = new JTextField();
            troll_number.setBounds(150,70,200,30);
            add(troll_number);

            branch = new JLabel("branch");
            branch.setBounds(30,110,100,30);
            add(branch);

            tbranch = new JTextField();
            tbranch.setBounds(150,110,200,30);
            add(tbranch);

            gender = new JLabel("geder");
            gender.setBounds(30,150,100,30);
            add(gender);

            male = new JRadioButton("male");
            male.setBounds(150,150,100,30);
            add(male);

            female = new JRadioButton("female");
            female.setBounds(250,150,100,30);
            add(female);

            g = new ButtonGroup();
            g.add(male);
            g.add(female);

            terms = new JCheckBox("accept terms and conditions");
            terms.setBounds(150,190,200,30);
            add(terms);

            submit = new JButton("submit");
            submit.setBounds(150,230,100,30);
            add(submit);

            reset = new JButton("reset");
            reset.setBounds(250,230,100,30);
            add(reset);

            setVisible(true);
  
        }

public static void main(String[] args) {
                new views();
            }
}
    
