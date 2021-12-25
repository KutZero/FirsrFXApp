package com.tusur.firsrfxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// класс для работы с базой данных
//Вообще, стоит сразу сказать, что данная датабаза берётся с локального сервера, то есть с машины, на которой я всё это писал.
//Значит для запуска на другой машине нужно будет не только иметь базу (я её добавлю в репрезенторий), но и создать сервер.
//Без базы данных, приложение не запустится. Никак. Вообще. Поэтому стоит сразу разобраться с этим.
//В README.txt файле будет расписано, как подключить SQL к данному IDE.
//Когда в импортах не будет ошибок, то подключение к модулю SQL успешно, дальше нужно подключить саму базу
public class BDController {
    public Connection databaseLink;
    public String DataBaseName = "base";// тут нужно указать название базы(кормит) данных, в которое были созданы таблицы
    public Connection getConnection()
    {
        String databaseName = DataBaseName; //название датабазы (всё ещё кормит)
        String databaseUser = "root"; //пользователь датабазы. Использовать необходимо те данные, которые  вы вводили при создании профиля базы данных SQL.
        String databasePassword = "root"; //пароль. Это вход в профиль базы данных, а не регистрация в нём.
        String url = "jdbc:mysql://localhost/" + databaseName; //URL для подключения базы

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("База данных подключена");
        }
        catch (Exception exp)
        {
            System.out.println("База данных не подключена, проверьте настройки");
            exp.printStackTrace();
        }
        return databaseLink;
    }
    public String getDataBaseName(){return DataBaseName;} //возврат названия базы данных для запросов


    //Запросы в базу данных для вставки/вывода данных
    //|=========================================================================================================================
    public int GetPrimaryQuestionCount(Statement stt) //получить количество вопросов для первичного теста
    {
        //на основе данного запроса разберу, как вообще они работают. Использовать этот гайд или нет - ваше право.
        int QuestCount = 0;
        {
            try {//любая часть, связанная с SQL в коде требует схему try+catch либо вызов ислючений в классе
                String GetQuestCount = "SELECT Count(*) FROM "+DataBaseName+ ".question"; //здесь создается сам запрос, на основе того, как бы он был вписан в SQL
                ResultSet Count_output = stt.executeQuery(GetQuestCount); //Выполнение созданной ранее строки
                while (Count_output.next()) {//если запрос был удачен, то будет реализован данный цикл
                    QuestCount = Count_output.getInt("Count(*)"); //переменная, в которую будет записан полученный результат. В качестве переменной функция getInt/getString принимает String название колоннки, из которой будет взята информация
                    System.out.println("Взятие количества вопросов успешно");//В случае удачного запроса в консоль будет выведено следующее сообщение. Само по себе позволяет просматривать статус работы приложения
                }
            } catch (Exception exp) {
                System.out.println("Взятие количества вопросов не удалось, проверьте запрос");//В случае неудачи в консоль будет выведено данное сообщение. Чаще всего проблема именно в строении запроса, так что исправл
            }
        } //узнать количество вопросов
        return QuestCount;//передача количествва, для дальнейшего использования в программе
    }
    public int getTasksCount(Statement stt) //получить количество вопросов для первичной истории (координатор)
    {
        int QuestCount = 0;
        {
            try {
                String GetQuestCount = "SELECT Count(*) FROM "+DataBaseName+ ".stories";
                ResultSet Count_output = stt.executeQuery(GetQuestCount);
                while (Count_output.next()) {
                    QuestCount = Count_output.getInt("Count(*)");
                    System.out.println("Взятие количества вопросов истории успешно");
                }
            } catch (Exception exp) {
                System.out.println("Взятие количества вопросов истории не удалось, проверьте запрос");
            }
        } //узнать количество вопросов
        return QuestCount;
    }
    public void UpdatePrimaryStatus(Statement stt, String DataBaseName, int Result)//изменить статус прохождения первичного теста
    {
        try {
            String PasteAsk = "UPDATE "+DataBaseName+".students SET  result_perq = "+Result+" WHERE NUM = 1";
            stt.executeUpdate(PasteAsk);
            System.out.println("Изменение статуса выполнения успешно");
        } catch (Exception exp) {
            System.out.println("Изменение статуса выполнения не успешно, проверьте запрос");
        }
    }
    public void UpdateStory1Status(Statement stt, String DataBaseName, int Result)//изменить статус прохождения первичного теста
    {
        try {
            String PasteAsk = "UPDATE "+DataBaseName+".students SET  result_story = "+Result+" WHERE NUM = 1";
            stt.executeUpdate(PasteAsk);
            System.out.println("Изменение статуса выполнения успешно");
        } catch (Exception exp) {
            System.out.println("Изменение статуса выполнения не успешно, проверьте запрос");
        }
    }
    public int GetRightStory1(Statement stt, BDController DB, String DataBaseName)//получение правильного ответа на вопросы первой истории и сравнивание с пользовательскими ответами
    {
        int Points = 0, max = DB.getTasksCount(stt), RightAnswer=-1, UserAnswer;
        for(int i = 0; i< max; i++)
        {
            try {
                String GetQuestCount = "SELECT numOtv FROM " +DataBaseName+ ".otvetsstories WHERE Pravilnost = 1 AND NumStory = "+(i+1);
                ResultSet Right_output = stt.executeQuery(GetQuestCount);
                while (Right_output.next()) {
                    RightAnswer = Right_output.getInt("numOtv");
                    System.out.println("Взятие правильного ответа успешно");
                }
            } catch (Exception exp) {
                System.out.println("Взятие правильного ответа не удалось, проверьте запрос");
            }
            UserAnswer =  GetQuestionInfoStory1(stt, DataBaseName,i+1);
            if(RightAnswer == UserAnswer)
            {
                Points++;
            }
        }
        return Points;
    }
    public int GetQuestionInfoStory1(Statement stt, String DataBaseName, int Question)//получение пользовательских ответов на вопросы в истории1
    {
        int UserAnswer=0;
        try {
            String GetQuestCount = "SELECT Otvet FROM "+DataBaseName+ ".stata_stories where Num = "+Question;
            ResultSet Right_output = stt.executeQuery(GetQuestCount);
            while (Right_output.next()) {
                UserAnswer = Right_output.getInt("Otvet");
                System.out.println("Взятие пользовательского ответа успешно");
            }
        } catch (Exception exp) {
            System.out.println("Взятие пользовательского ответа не удалось, проверьте запрос");
        }
        return UserAnswer;
    }
    public int GetRightAnswers(Statement stt, BDController DB, String DataBaseName)//получение правильных ответов на первичный тест
    {
        int Points = 0, max = DB.GetPrimaryQuestionCount(stt), RightAnswer=-1, UserAnswer;
        for(int i = 0; i< max; i++)
        {
            try {
                String GetQuestCount = "SELECT num_otv FROM " +DataBaseName+ ".otvetsperq WHERE Pravilnost = 1 AND NUM_VOPR = "+(i+1);
                ResultSet Right_output = stt.executeQuery(GetQuestCount);
                while (Right_output.next()) {
                    RightAnswer = Right_output.getInt("num_otv");
                    System.out.println("Взятие правильного ответа успешно");
                }
            } catch (Exception exp) {
                System.out.println("Взятие правильного ответа не удалось, проверьте запрос");
            }
            UserAnswer = GetQuestionInfo(stt, DataBaseName,i+1);
            if(RightAnswer == UserAnswer)
            {
                Points++;
            }
        }
        return Points;
    }
    public int GetQuestionInfo(Statement stt, String DataBaseName, int Question)//получение пользовательских ответов на первичный тест
    {
        int UserAnswer=0;
        try {
            String GetQuestCount = "SELECT Otvet FROM "+DataBaseName+ ".stata_pertest where Num = "+Question;
            ResultSet Right_output = stt.executeQuery(GetQuestCount);
            while (Right_output.next()) {
                UserAnswer = Right_output.getInt("Otvet");
                System.out.println("Взятие пользовательского ответа успешно");
            }
        } catch (Exception exp) {
            System.out.println("Взятие пользовательского ответа не удалось, проверьте запрос");
        }
        return UserAnswer;
    }
    public int GetUsers(Statement stt)//получение количества пользователей в базе данных
    {
        int Users=-1;
        try {
            String GetUserCount = "SELECT Count(*) FROM "+DataBaseName+ ".students";
            ResultSet Count_output = stt.executeQuery(GetUserCount);
            while (Count_output.next()) {
                Users = Count_output.getInt("Count(*)");
                System.out.println("Взятие количества польователей успешно");
            }
        } catch (Exception exp) {
            System.out.println("Взятие количества пользователей не удалось, проверьте запрос");
        }
        return Users;
    }
    public int GetLogin(Statement stt, String InputLogin, String InputPassword)//вход в профиль пользователя
    {
        String Login=" ";
        for(int i=0; i<GetUsers(stt); i++)
        {
            try {
                String GetQuestCount = "SELECT Login FROM "+DataBaseName+ ".students where Num = "+(i+1);
                ResultSet Right_output = stt.executeQuery(GetQuestCount);
                while (Right_output.next()) {
                    Login = Right_output.getString("Login");
                    System.out.println("Взятие пользовательского логина успешно");

                }
                if(Login.equals(InputLogin))
                {
                    System.out.println("Логин пройден");
                    if(InputPassword.equals(GetPassword(stt,i+1)))
                    {
                        System.out.println("вы зашли!");
                        return i+1;
                    }
                }
            } catch (Exception exp) {
                System.out.println("Взятие пользовательского логина не удалось, проверьте запрос");
            }
        }
        return -1;
    }
    public String GetPassword(Statement stt, int User)
    {
        String Password=" ";
            try {
                String GetQuestCount = "SELECT Password FROM "+DataBaseName+ ".students where Num = "+User;
                ResultSet Right_output = stt.executeQuery(GetQuestCount);
                while (Right_output.next()) {
                    Password = Right_output.getString("Password");
                    System.out.println("Взятие пользовательского пароля успешно");
                }
            } catch (Exception exp) {
                System.out.println("Взятие пользовательского пароля не удалось, проверьте запрос");
            }
        return Password;
    }
    /*public String GetActiveName(Statement stt, int  num)
    {
        String Name = " ";
        try {
            String GetQuestCount = "SELECT Name FROM "+DataBaseName+ ".students where Num = "+(num);
            ResultSet Right_output = stt.executeQuery(GetQuestCount);
            while (Right_output.next()) {
                Name = Right_output.getString("Name");
                System.out.println("Взятие пользовательского логина успешно");

            }
        } catch (Exception exp) {
            System.out.println("Взятие пользовательского логина не удалось, проверьте запрос");
        }
        return Name;
    }*/ //как-нибудь поймёте, что эта штука делает, разберётесь, нужно оно или нет
    public void AddUser(Statement stt, String Login, String Password, String Name, String Second_name, String otchestvo)//регистрация пользователя и добавление его данных в базу
    {
        try {
            String PasteAsk = "INSERT INTO " + DataBaseName + ".students (Num, Login, Password, Name, Second_name, otchestvo) VALUES (" + (GetUsers(stt) + 1) + ", " + Login + ", " + Password + ", " + Name +", " + Second_name +", " + otchestvo + ")";
            stt.executeUpdate(PasteAsk);
            System.out.println("Вставка пользователя успешна");
        } catch (Exception exp) {
            System.out.println("Вставка пользователя не удалась, проверьте запрос");
        }//создать запись
    }

}
