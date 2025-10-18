package ma.projet.inserts;

import ma.projet.civil.beans.*;
import ma.projet.civil.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InsertCivil {
    public static void main(String[] args) {
        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            

            Homme homme1 = new Homme("SAFI", "SAID", "0612345678", "Casablanca", sdf.parse("01/01/1960"));
            Homme homme2 = new Homme("ALAMI", "AHMED", "0612345679", "Rabat", sdf.parse("15/05/1965"));
            Homme homme3 = new Homme("BENALI", "MOHAMED", "0612345680", "Fès", sdf.parse("20/03/1970"));
            Homme homme4 = new Homme("CHAFIK", "YOUSSEF", "0612345681", "Marrakech", sdf.parse("10/07/1975"));
            Homme homme5 = new Homme("DAOUDI", "KARIM", "0612345682", "Tanger", sdf.parse("25/12/1980"));
            
            hommeService.create(homme1);
            hommeService.create(homme2);
            hommeService.create(homme3);
            hommeService.create(homme4);
            hommeService.create(homme5);
            

            Femme femme1 = new Femme("SALIMA", "RAMI", "0612345683", "Casablanca", sdf.parse("05/02/1965"));
            Femme femme2 = new Femme("AMAL", "ALI", "0612345684", "Rabat", sdf.parse("10/06/1970"));
            Femme femme3 = new Femme("WAFA", "ALAOUI", "0612345685", "Fès", sdf.parse("15/09/1975"));
            Femme femme4 = new Femme("KARIMA", "ALAMI", "0612345686", "Marrakech", sdf.parse("20/11/1980"));
            Femme femme5 = new Femme("FATIMA", "BENALI", "0612345687", "Tanger", sdf.parse("25/01/1985"));
            Femme femme6 = new Femme("AICHA", "CHAFIK", "0612345688", "Casablanca", sdf.parse("30/04/1990"));
            Femme femme7 = new Femme("ZINEB", "DAOUDI", "0612345689", "Rabat", sdf.parse("05/08/1995"));
            Femme femme8 = new Femme("KHADIJA", "ELAMI", "0612345690", "Fès", sdf.parse("10/10/2000"));
            Femme femme9 = new Femme("MARIAM", "BENALI", "0612345691", "Marrakech", sdf.parse("15/12/2005"));
            Femme femme10 = new Femme("NAJAT", "CHAFIK", "0612345692", "Tanger", sdf.parse("20/03/2010"));
            
            femmeService.create(femme1);
            femmeService.create(femme2);
            femmeService.create(femme3);
            femmeService.create(femme4);
            femmeService.create(femme5);
            femmeService.create(femme6);
            femmeService.create(femme7);
            femmeService.create(femme8);
            femmeService.create(femme9);
            femmeService.create(femme10);
            

            Mariage mariage1 = new Mariage(sdf.parse("03/09/1990"), null, 4);
            mariage1.setHomme(homme1);
            mariage1.setFemme(femme1);
            mariageService.create(mariage1);
            
            Mariage mariage2 = new Mariage(sdf.parse("03/09/1995"), null, 2);
            mariage2.setHomme(homme1);
            mariage2.setFemme(femme2);
            mariageService.create(mariage2);
            
            Mariage mariage3 = new Mariage(sdf.parse("04/11/2000"), null, 3);
            mariage3.setHomme(homme1);
            mariage3.setFemme(femme3);
            mariageService.create(mariage3);
            
            Mariage mariage4 = new Mariage(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0);
            mariage4.setHomme(homme1);
            mariage4.setFemme(femme4);
            mariageService.create(mariage4);
            

            Mariage mariage5 = new Mariage(sdf.parse("01/01/1995"), null, 2);
            mariage5.setHomme(homme2);
            mariage5.setFemme(femme5);
            mariageService.create(mariage5);
            
            Mariage mariage6 = new Mariage(sdf.parse("01/01/2000"), null, 1);
            mariage6.setHomme(homme2);
            mariage6.setFemme(femme6);
            mariageService.create(mariage6);
            

            Mariage mariage7 = new Mariage(sdf.parse("01/01/2000"), null, 1);
            mariage7.setHomme(homme3);
            mariage7.setFemme(femme7);
            mariageService.create(mariage7);
            
            Mariage mariage8 = new Mariage(sdf.parse("01/01/2005"), null, 2);
            mariage8.setHomme(homme3);
            mariage8.setFemme(femme8);
            mariageService.create(mariage8);
            
            Mariage mariage9 = new Mariage(sdf.parse("01/01/2010"), null, 1);
            mariage9.setHomme(homme3);
            mariage9.setFemme(femme9);
            mariageService.create(mariage9);
            
            Mariage mariage10 = new Mariage(sdf.parse("01/01/2015"), null, 3);
            mariage10.setHomme(homme3);
            mariage10.setFemme(femme10);
            mariageService.create(mariage10);
            
            System.out.println("Civil data inserted successfully!");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
