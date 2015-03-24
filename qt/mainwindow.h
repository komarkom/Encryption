#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "mycrypo.h"
#include "QString"
#include <QFileDialog>
#include <QTextCodec>
#include <time.h>
#include <QFile>
#include <QTextStream>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

    QString fileName;

    MyCrypo *cryp;

private slots:
    void on_CrypRadioButt_clicked();

    void on_GeneratePushButton_clicked();

    void on_FirstNextButton_clicked();

    void on_DecrypRadioButton_clicked();

    void on_HomeButton_clicked();

    void on_ExecuteButton_clicked();

    void on_openfile_clicked();

    void on_savefile_clicked();

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
