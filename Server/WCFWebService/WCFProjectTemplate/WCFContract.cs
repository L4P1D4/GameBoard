using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Serialization;
using System.ServiceModel;


namespace WCFSample
{
    [DataContract]
    public class EchoMessage
    {
        private string _OutMessage;
        private string _ReturnMessage;

        [DataMember]
        public string OutMessage
        {
            get { return _OutMessage; }
            set { _OutMessage = value; }
        }

        [DataMember]
        public string ReturnMessage
        {
            get { return _ReturnMessage; }
            set { _ReturnMessage = value; }
        }
    }
}
