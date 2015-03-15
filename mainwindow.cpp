#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->FirstPage->show();
    ui->SecondPage->hide();
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::on_GeneratePushButton_clicked()
{
    str = ui->keyValue->toPlainText();
    int t = 4;
    t++;
    ui->keyValue->setText("gsdgsd");
}

void MainWindow::on_CrypRadioButt_clicked()
{
    ui->labelTwo->setText("Crypto-text");
    ui->labelOne->setText("Text");
}

void MainWindow::on_DecrypRadioButton_clicked()
{
    ui->labelOne->setText("Crypto-text");
    ui->labelTwo->setText("Text");
}

void MainWindow::on_FirstNextButton_clicked()
{
    ui->FirstPage->hide();
    ui->SecondPage->show();
}

void MainWindow::on_HomeButton_clicked()
{
    ui->FirstPage->show();
    ui->SecondPage->hide();
}
