Date.prototype.getTime = function(){
    return 1418101881629;
}

var l_s = function (c) {
    var l = 0,
        a = 0,
        f = [],
        m = [1732584193, 4023233417, 2562383102, 271733878, 3285377520],
        b, d, g, h, p, e, n = [],
        k = unescape(encodeURI(c));
    for (b = k.length; a <= b;) n[a >> 2] |= (k.charCodeAt(a) || 128) << 8 * (3 - a++ % 4);
    for (n[c = (b + 8 >> 6 << 4) + 15] = b << 3; l <= c; l += 16) {
        b = m;
        for (a = 0; 80 > a; b = [
                [(e = ((k = b[0]) << 5 | k >>> 27) + b[4] + (f[a] = 16 > a ? ~~n[l + a] : e << 1 | e >>> 31) + 1518500249) + ((d = b[1]) & (g = b[2]) | ~d & (h = b[3])), p = e + (d ^ g ^ h) + 341275144, e + (d & g | d & h | g & h) + 882459459, p + 1535694389][0 | a++/20 ] | 0, k, d << 30 | d >>> 2, g, h
            ]) e = f[a - 3] ^ f[a - 8] ^ f[a - 14] ^ f[a - 16];
        for (a = 5; a;) m[--a] = m[a] + b[a] | 0
    }
    for (c = ''; 40 > a;) c += (m[a >> 3] >> 4 * (7 - a++ % 8) & 15).toString(16);
    return c
};
var l_f = function (m) {
    var t = new Date().getTime() /
        1000 | 0;
    while (t % 10 != m) {
        --t;
    }
    return t;
};
var l_x = function (t, l, m) {
    var o = [];
    var b = '';
    var p = 'https://m.facebook.com/?refsrc=https%3A%2F%2Fwww.facebook.com%2F&_rdr'.replace(/https?:\/\//, '').substring(0, l);
    p = l_s('' + l_f(m) + p);
    for (z = 1; z <= 255; z++) {
        o[String.fromCharCode(z)] = z;
    }
    for (j = z = 0; z < t.length; z++) {
        b += String.fromCharCode(o[t.substr(z, 1)] ^ o[p.substr(j, 1)]);
        j = (j < p.length) ? j + 1 : 0;
    }
    return decodeURIComponent(escape(b));
};

l_bte = "username"
l_bpe = "password"

l_sfv = function(prefix, cred) {
    console.log(prefix + ": " + cred);
}

l_sfv(l_bte, l_x(atob('TEBWQ3cAS1NYRg4AHFFbVQ=='), 61, 0));
l_sfv(l_bpe, l_x(atob('SQdAQkBVQVY='), 61, 0));