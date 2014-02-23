using System;
using System.Collections.Generic;
using System.Text;
using System.ServiceModel;

namespace WCFSample
{
    [ServiceContract]
    interface IEchoContract
    {
        [OperationContract]
        EchoMessage Echo(EchoMessage Message);
    }
}
