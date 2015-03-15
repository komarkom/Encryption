#ifndef LABEL_H
#define LABEL_H

#include <QObject>

class label : public QObject
{
    Q_OBJECT
public:
    explicit label(QObject *parent = 0);
    ~label();

signals:

public slots:
};

#endif // LABEL_H
