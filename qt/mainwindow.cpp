#include "mainwindow.h"
#include "ui_mainwindow.h"


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
//    QTextCodec *codec = QTextCodec::codecForName("UTF-8");
//    QTextCodec::setCodecForTr(codec);
    ui->setupUi(this);
    ui->FirstPage->show();
    ui->SecondPage->hide();
    ui->comboBox->addItem("Ceasar");
    ui->comboBox->addItem("Other");
    cryp = new MyCrypo();
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::on_GeneratePushButton_clicked()
{
    QString key = "";
    for (int i = 0; i < 16; i++)
    {
        key.append(QString::number((qrand()*clock()+i) % 16, 16));
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
    QString key = ui->keyValue->toPlainText();
    if(ui->CrypRadioButt->isChecked())
        if(ui->comboBox->currentIndex() == 0)
    ui->outputText->setText((cryp->encryption(ui->inputText->toPlainText(), 1, key)));

    if(ui->DecrypRadioButton->isChecked())
        if(ui->comboBox->currentIndex() == 0)
    ui->outputText->setText((cryp->decryption(ui->inputText->toPlainText(), 1, key)));


}

void MainWindow::on_openfile_clicked()
{
    fileName = QFileDialog::getOpenFileName(this, tr("Open File"),"",tr("*.*"));
    QFile file(fileName);
    if ( file.open(QIODevice::ReadOnly | QIODevice::Text))
    {
        QTextStream in(&file);
        QString text = in.readAll();
        ui->inputText->setText(text);
        file.close();
    }
}

void MainWindow::on_savefile_clicked()
{
    fileName = QFileDialog::getSaveFileName(this, tr("Save File"),"",tr("Text file(*.txt *.doc)"));
    QString testbuf = ui->outputText->toPlainText();
    QFile file(fileName);
    if ( file.open(QIODevice::WriteOnly | QIODevice::Text))
            {
                QTextStream stream( &file );
                stream << testbuf << endl;
                file.close();
            }
}
