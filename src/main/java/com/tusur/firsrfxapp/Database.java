package com.tusur.firsrfxapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Вообще, стоит сразу сказать, что данная датабаза берётся с локального сервера, то есть с машины, на которой я всё это писал.
//Значит для запуска на другой машине нужно будет не только иметь базу (я её добавлю в репрезенторий), но и создать сервер.
public class Database
    {
     public Connection databaseLink;
     public String DataBaseName = "base"; // тут нужно указать название базы(кормит) данных, в которое были созданы таблицы
     public Connection getConnection()
         {
          String databaseName = DataBaseName; //название датабазы (всё ещё кормит)
          String databaseUser = "root"; //пользователь датабазы
          String databasePassword = "root"; //пароль
          String url = "jdbc:mysql://localhost/" + databaseName;

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
         public String getDataBaseName(){return DataBaseName;}

        public int GetPrimaryQuestionCount(Statement stt) //получить количество вопросов для первичного теста
        {
            int QuestCount = 0;
            {
                try {
                    String GetQuestCount = "SELECT Count(*) FROM "+DataBaseName+ ".question";
                    ResultSet Count_output = stt.executeQuery(GetQuestCount);
                    while (Count_output.next()) {
                        QuestCount = Count_output.getInt("Count(*)");
                        System.out.println("Взятие количества вопросов успешно");
                    }
                } catch (Exception exp) {
                    System.out.println("Взятие количества вопросов не удалось, проверьте запрос");
                }
            } //узнать количество вопросов
            return QuestCount;
        }
            public int getTasksCount(Statement stt) //получить количество вопросов для первичной истории
        {
            int QuestCount = 0;
            {
                try {
                    String GetQuestCount = "SELECT Count(*) FROM "+DataBaseName+ ".otvetsstories";
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
        public void UpdatePrimaryStatus(Statement stt, String DataBaseName, float Result, int StudentNumber)//изменить статус прохождения первичного теста
        {
                try {
                    String PasteAsk = "UPDATE "+DataBaseName+".students SET  result_perq = "+Result+" WHERE NUM = "+StudentNumber;
                    stt.executeUpdate(PasteAsk);
                    System.out.println("Изменение статуса выполнения успешно");
                } catch (Exception exp) {
                    System.out.println("Изменение статуса выполнения не успешно, проверьте запрос");
                }
        }
    }
