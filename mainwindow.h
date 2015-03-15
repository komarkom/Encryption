#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "mycrypo.h"
#include "QString"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

    QString str;

private slots:
    void on_CrypRadioButt_clicked();

    void on_GeneratePushButton_clicked();

    void on_FirstNextButton_clicked();

    void on_DecrypRadioButton_clicked();

    void on_HomeButton_clicked();

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
