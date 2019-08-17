'use strict';

const functions = require('firebase-functions');
const braintree = require("braintree");

const admin = require('firebase-admin');
admin.initializeApp();

const runtimeOptions = {
    timeoutSeconds: 313,
  }

exports.braintreeClientToken = functions.runWith(runtimeOptions).https.onRequest(async (req, res) => {
    console.log('Execute BrainTree ClientToken Process');

    var gateway = braintree.connect({
        environment:  braintree.Environment.Sandbox,
        merchantId:   '4t4xz6vnm6dbk5jt',
        publicKey:    'jp5pcq5jw2qymjxw',
        privateKey:   '719393e5254462bdc7b894a2d3e5ef7a'
    });

    return gateway.clientToken.generate({}, function (err, response) {
            if (err) {
                 throw new functions.https.HttpsError('Client Token Error', response);
            } else {
                var clientTokenResponse = response.clientToken
                console.log('BrainTree ClientToken ::: ' + clientTokenResponse);

                var callBackResult = {
                    data: {
                      "ClientTokenResponse": clientTokenResponse,
                    },
                };
                res.send(callBackResult);                
            }
        }
    );
});

exports.createTransaction = functions.runWith(runtimeOptions).https.onCall((data, context) => {
    const paymentMethodNonceFromClient = data.paymentMethodNonceFromClient;
    console.log('Execute BrainTree Transaction Process');

    var gateway = braintree.connect({
        environment:  braintree.Environment.Sandbox,
        merchantId:   '4t4xz6vnm6dbk5jt',
        publicKey:    'jp5pcq5jw2qymjxw',
        privateKey:   '719393e5254462bdc7b894a2d3e5ef7a'
    });

    return gateway.transaction.sale({
        amount: "13.39",
        paymentMethodNonce: paymentMethodNonceFromClient,
        options: {
          submitForSettlement: true
        }
      }, function (err, result) {
        if (err) {
            throw new functions.https.HttpsError('Client Token Error', response);
        } else {
           var createTransactionResponse = response;
           console.log('BrainTree CreateTransaction ::: ' + createTransactionResponse);

           var callBackResult = {
               data: {
                 "CreateTransactionResponse": createTransactionResponse,
               },
           };
           res.send(callBackResult);
       }
    });
});