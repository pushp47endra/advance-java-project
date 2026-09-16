package extra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


 public class swing extends JFrame implements ActionListener{
        
        JLabel name , roll , branch , gender;
        JTextField tname , troll , tbranch; 
        JRadioButton male , female; 
        JCheckBox terms; 
        JButton submit,reset; 
        ButtonGroup g;





        swing(){
            setSize(500,500);
            setLayout(null);
            setTitle("student registration form ");
            


            name = new JLabel("full name ");
            name.setBounds(30,30,100,30);

            add(name);
            tname = new JTextField();
           tname.setBounds(150,30,200,30);
            add(tname);

            roll = new JLabel("roll number ");
            roll.setBounds(30,70,100,30);
            add(roll);

            troll =new JTextField();
           troll.setBounds(150,70,200,30);
            add(troll);
            
           System.out.println(troll.getText());
            
           branch = new JLabel("enter branch ");
           branch.setBounds(30,110,100,30);
           add(branch);

           tbranch = new JTextField();
           tbranch.setBounds(150,110,200,30);
           add(tbranch);

            gender = new JLabel("gender");
            gender.setBounds(30,150,100,30);
            add(gender);

            male = new JRadioButton("male");
            male.setBounds(150,150,80,30);
            add(male);

            female = new JRadioButton("female");
            female.setBounds(240,150,90,30);       
            add(female);

            g = new ButtonGroup();
            g.add(male);
            g.add(female);

        
            terms = new JCheckBox("accept terms and conditions");
            terms.setBounds(30,190,300,30);
            add(terms);

            submit = new JButton("submit");
            submit.setBounds(120,240,100,30);
            submit.addActionListener(this);
            add(submit);

            reset = new JButton("reset");
            reset.setBounds(240,240,100,30);
            reset.addActionListener(this);
            add(reset);

            setVisible(true);

        }
@Override
public void actionPerformed(ActionEvent e){

        if(e.getSource()==reset){
            
            tname.setText("");
            troll.setText("");
            tbranch.setText("");
            g.clearSelection();
            terms.setSelected(false);

        }



        String real_gender = "";
        if (male.isSelected()){
            real_gender ="Male";
        }
        else{
            real_gender="Female";
        }


        if (!terms.isSelected()){
            JOptionPane.showMessageDialog(this , "please accept Terms and conditions");
            return;
        }


        if (e.getSource()==submit){
        JOptionPane.showMessageDialog(this, "registration successfull \n"


        +"full name :"+tname.getText()
        +"\nroll number :"+troll.getText()
        +"\nbranch :"+tbranch.getText()
        +"\ngender :"+ real_gender);

        
            tname.setText("");
            troll.setText("");
            tbranch.setText("");
            g.clearSelection();
            terms.setSelected(false);

        }





}


public static void main(String[] args){
    new swing();

}
}




