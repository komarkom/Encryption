#include "mycrypo.h"

MyCrypo::MyCrypo()
{

}

MyCrypo::MyCrypo(QString val)
{
    this->inputText = val;
}

void MyCrypo::setInputText(QString val)
{
    this->inputText = val;
}

void MyCrypo::setOutputText(QString val)
{
    this->outputText = val;
}

QString MyCrypo::getInputText()
{
    return this->inputText;
}

QString MyCrypo::getOutputText()
{
    return this->outputText;
}

QString MyCrypo::encryption(QString text, int type, QString key)
{
    text.append("coding").append(" ").append(key).append(type);
    return text;
}

QString MyCrypo::decryption(QString text, int type, QString key)
{
    text.append("decoding").append(" ").append(key).append(type);
    return text;
}

MyCrypo::~MyCrypo()
{

}

