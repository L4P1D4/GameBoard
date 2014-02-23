using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestServer
{
    public interface IUser
    {
        string Name { get; set; }
        string Email { get; set; }
        string Password { get; set; }

    }
}
