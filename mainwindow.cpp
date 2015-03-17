#include "mainwindow.h"
#include "ui_mainwindow.h"


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->FirstPage->show();
    ui->SecondPage->hide();
    ui->comboBox->addItem("1");

    cryp = new MyCrypo();
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::on_GeneratePushButton_clicked()
{
    str = ui->keyValue->toPlainText();
    QString key = "";
    for (int i = 0; i < 16; i++)
    {
        key.append(QString::number(qrand() % 16, 16));
    }
    ui->keyValue->setText(key);
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

void MainWindow::on_ExecuteButton_clicked()
{
    if(ui->CrypRadioButt->isChecked())
    ui->outputText->setText(cryp->encryption(ui->inputText->toPlainText(), 1, str));

    if(ui->DecrypRadioButton->isChecked())
    ui->outputText->setText(cryp->decryption(ui->inputText->toPlainText(), 1, str));


}
