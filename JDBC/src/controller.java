import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



public class controller implements ActionListener {

     model m ;
     views v;
     controller(model m, views v){
        this.m = m;
        this.v = v;

        v.submit.addActionListener(this);
        v.reset.addActionListener(this);
        
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==v.reset){
            v.tname.setText("");
            v.troll_number.setText("");
            v.tbranch.setText("");
            v.g.clearSelection();
            v.terms.setSelected(false);
             System.out.println("reset done");
        }
        String gender = "";
        if(v.male.isSelected()){
                gender = "male";
            }
            if(v.female.isSelected()){
                gender = "female";
            }

        if (!v.terms.isSelected()){
            // JOptionPane.showMessageDialog(this , "please accept Terms and conditions");
             System.out.println("terms not selected");
            return;
        }

        if(e.getSource() == v.submit){
            
            String name = String.valueOf(v.tname.getText());
            String roll_number = String.valueOf(v.troll_number.getText());
            String branch = String.valueOf(v.tbranch.getText());
            
            
            try {
                m.storeData(name, roll_number, branch, gender);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }





        if (e.getSource()==v.submit){
        // JOptionPane.showMessageDialog(this, "registration successfull \n"


        // +"full name :"+v.tname.getText()
        // +"\nroll number :"+v.troll_number.getText()
        // +"\nbranch :"+v.tbranch.getText()
        // +"\ngender :"+ gender);

        
            v.tname.setText("");
            v.troll_number.setText("");
            v.tbranch.setText("");
            v.g.clearSelection();
            v.terms.setSelected(false);
            System.out.println("submit done");
        }












    }
}
