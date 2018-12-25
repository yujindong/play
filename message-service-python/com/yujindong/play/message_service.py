# coding: utf-8
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import smtplib
from email.mime.text import MIMEText
from email.header import Header

from com.yujindong.play.api import MessageService

sender = 'yujd@133.cn'
authCode = 'parbUtHDgHugZGfL'
class MessageServiceHandler:
    def sendMobileMessage(self, mobile, message):
        print ("sendMobileMessage: mobile=" + mobile + " message=" + message)
        return True
    def sendEmailMessage(self, email, message):
        print ("sendEmailMessage")
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj['From'] = sender
        messageObj['To'] = email
        messageObj['Subject'] = Header("发送邮件服务测试", "utf-8")
        try:
            smtpObj = smtplib.SMTP("smtp.qiye.163.com")
            smtpObj.login(sender, authCode)
            smtpObj.sendmail(sender, [email], messageObj.as_string())
            print ("send email success")
        except smtplib.SMTPException as ex:
            print ("semd email failed")
            print (ex)
            return False

        return True

if __name__ == '__main__':
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket(None, "50003")
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print ("python thrift server start")
    server.serve()
    print ("python thrift server stop")