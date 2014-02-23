using System;
using System.Collections.Generic;
using System.Text;
using System.ServiceModel;
using System.Data;
using System.Data.SqlClient;

namespace WCFSample
{
    class EchoImplementation : IEchoContract
    {
        public EchoMessage Echo(EchoMessage Message)
        {
            EchoMessage _returningMessage = new EchoMessage();

            _returningMessage.ReturnMessage = Message.OutMessage;

            return _returningMessage;
        }
    }
}
