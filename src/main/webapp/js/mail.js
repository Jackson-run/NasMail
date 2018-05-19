'use strict'
var MailEnvelopeIteam = function (text) {
    if (text) {
        var o = JSON.parse(text);
        this.from = o.from;
        this.to = o.to;
        this.account = new BigNumber( o.account);
        this.id = o.id;
        this.title=o.title;
        this.content = o.content;
        this.time = o.time;

    } else {
        this.from = "";
        this.to = "";
        this.account = new BigNumber(0);
        this.title="";
        this.content = "";
        this.time = "";
        this.id = "";
    }
};

MailEnvelopeIteam.prototype = {
    toString: function () {
        return JSON.stringify(this);
    }
};

var MailEnvelopeId = function (text) {
    if (text) {
        var o = JSON.parse(text);
        this.ids = o.ids;
    } else {
        this.ids = []
    }
};

MailEnvelopeId.prototype = {
    toString: function () {
        return JSON.stringify(this);
    }
};


var MailEnvelope = function () {
    LocalContractStorage.defineMapProperty(this, "mail", {
        parse: function (text) {
            return new MailEnvelopeIteam(text);
        },
        stringify: function (o) {
            return o.toString();
        }
    });
    LocalContractStorage.defineMapProperty(this, "id", {
        parse: function (text) {
            return new MailEnvelopeId(text);
        },
        stringify: function (o) {
            return o.toString();
        }
    });
};


MailEnvelope.prototype = {
    init: function () {
    },
    sent: function (mailId,title,content,address,time) {
        var value = Blockchain.transaction.value;
        var from = Blockchain.transaction.from;

        var mailIds = this.id.get(from);
        if (!mailIds) {
            mailIds = new MailEnvelopeId()
        }
        var mailItem = new MailEnvelopeIteam();
        mailItem.from = from;
        mailItem.account = value;
        mailItem.title = title;
        mailItem.id = mailIds;
        mailItem.content = content;
        mailItem.to = address;
        mailItem.time = time;

        mailIds.ids.push(mailId);
        this.mail.put(from, mailItem);
        this.id.put(from, mailIds);
        return "success";
    },
    read: function () {
        var from = Blockchain.transaction.from;
        return this.mail.get(from);
    },
    ids: function () {
        var address = Blockchain.transaction.from;
        return this.id.get(address);
    },
    message: function (mail_id) {
        var address = Blockchain.transaction.from;
        return this.mail.get(address + "_" + mail_id);
    }
};
module.exports = MailEnvelope;
