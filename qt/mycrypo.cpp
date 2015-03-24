#include "mycrypo.h"

MyCrypo::MyCrypo()
{

}

//MyCrypo::MyCrypo(QString val)
//{
//    this->inputText = val;
//}

//void MyCrypo::setInputText(QString val)
//{
//    this->inputText = val;
//}

//void MyCrypo::setOutputText(QString val)
//{
//    this->outputText = val;
//}

//QString MyCrypo::getInputText()
//{
//    return this->inputText;
//}

//QString MyCrypo::getOutputText()
//{
//    return this->outputText;
//}

QString MyCrypo::encryption(QString text, int type, QString key)
{
//    text.append("coding").append(" ").append(key).append(type);
    return Caesar(text, key, 1);
}

QString MyCrypo::decryption(QString text, int type, QString key)
{
//    text.append("decoding").append(" ").append(key).append(type);

    return Caesar(text, key, -1);
}

QString MyCrypo::Caesar(QString text, QString key, int route)
{
    if (!text.isEmpty() && !text.isNull() && !key.isNull() && !key.isEmpty())
    {
        QString out = "";
        QString tmp = "";
        bool ok = 0;
        int j = 0;
        int crypCode = 0;
        while(!ok && !key[j].isNull())
        {
            tmp = key[j];
            tmp.toInt(&ok);
            if (tmp == "0") ok = 0;
            j++;
        }
        if (!ok)
            crypCode = 5;
        else
            crypCode = tmp.toInt();
        char t;
        for (int i = 0; i < text.length(); i++)
        {
            t = text[i].toLatin1() + crypCode*route;
            out.append(t);
        }
        return out;
    }
    else return "not found text or key";
}

MyCrypo::~MyCrypo()
{

}

