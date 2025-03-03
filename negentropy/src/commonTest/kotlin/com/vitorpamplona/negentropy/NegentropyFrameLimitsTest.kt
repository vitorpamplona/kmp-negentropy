/**
 * Copyright (c) 2024 Vitor Pamplona
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.vitorpamplona.negentropy

import com.vitorpamplona.negentropy.testutils.StorageAssets.storageFrameLimitsClient
import com.vitorpamplona.negentropy.testutils.StorageAssets.storageFrameLimitsServer
import kotlin.test.Test
import kotlin.test.assertEquals

class NegentropyFrameLimitsTest {
    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testReconcile() {
        val clientDB = storageFrameLimitsClient()
        val serverDB = storageFrameLimitsServer()

        assertEquals(111, clientDB.size())
        assertEquals(147, serverDB.size())

        val neClient = Negentropy(clientDB, 4096)
        val neServer = Negentropy(serverDB, 4096)

        val init = neClient.initiate()

        assertEquals("6186a08fb96f0001e29f11be54fa53f72720e677babee588970200012c8ac25ca5bbdd28c43253a96d6fb95e8f2b0001952a5373d4250dbd5040fcf8859888d69e210001c528225ecd4006ac5b063ad09a70ae78a3300001cfa65482d53eac9a967c1a842e137f9b8926000168e70c76a5e53144805ec7289e700332977e000117e61639dd4802e7b2089d194a00bdb4a46b0001d5e63a9c54cd71cbd16540d0e15f0dd1933f0001a50d6a2d8b723a67fe87a3d13c0318498e200001c8e193177fb598bee7ff6f0c765ae07b9a3a0001dc08e6b32392bb680a5346a2fc805f9f97630001569332c9fb7575479f4b4b8eb63394e38a1300010cd018760a77541f67cd80db66a2f6b6956400010c0f3028dc6ecbe2eb55d30a865e057eac0d0001fb82fe7f996601e3c5a40250554babd400000155919fa4df10b52772cbe6340aca9b0d", init.toHexString())

        // send to the server.
        var result = neServer.reconcile(init)

        assertEquals(
            "6186a08fb96f00020844d5728f57c4aaae4e5c22a88e9f18781a75dea15dc6157f6487d7fda2abdd4d1b7839c6b613519baa8c5930a2d0fcb75e51d09cd6bcbf008db9c2d5e70b11d893a835d4faf5010ac57e563ea44c7d5107e3c5c021834292a68c575a2aad5edfccd2e0e5d0ff68ea4fdcbc78c9430c9e9027fd8151209e53d8d04338441d1311a9ee4570ebcd9a5e81054ca50b44c57098214982c639d241045c4ae68687ad0b9b5f018c1c508e4ee836445e6c7d0501b5773cb975b73c37a86cb438c1d656df1863d6e68af7f2c341787a6301771d07801d110d95ee1ba8b211bd9201781a27ca047f2a40edb443bab8dc31929f8e5457d9deac5415c287a090a28a2efdf63b9702000205e28d417be1c0c0242539d1ed7b8428165ecc13a1fa2fa66c9ca48de3f98249c99a92950a4498c11158d8b084be8048b38b78b18351dda2f41c278338d39204d2b17e27b1c6cdc9c7c1d4cf538b3ed6f8cc684a4cb8d125838f4cb5fd7f31f1d5795fd43ca000350a881c23eba888ef458a940bf355b58ea89daddce28a1ec31accd8d51741896ab3bfd4f819e4034a267fbbdc0e539ea8a05d53c73ac1312f958f2b000207bed74e251ffaa2ba558efdd868857c251a5017ec2cb25ebd2e18856d3902f8b43d0135483e9f47c64a1e824016845fa4395716947942611f532f52eda322d7d4b3489cf0594b805a4a45c25327b12ac7c932a02027d36e5f37f8cb53c37d47e7774ee3ba38031494956d5a68822151693f61f2be3c81c376ee162e8dc447bea0b7c2ad1217c41cca1b0fcbbf4e43ca82ba7d92853065a68dc59c8c048c52e966ef3e606c1b9291b7ba1eaa119b431763ae8a41c0da9f60c8df81e6cb85f8fc7576c6156c1e307e8337fff6ef8fdedd27d5098d9579210828b6a0c2ba8f08cf719e2100020d273a1fc63de456f9d18d4b63ee02e91dd5330f54b0e44e9e568eecbe093c3f049235ac2e05e532a50723f50b1484edd91cbea1ee9c02e9a7e5fc6ddbf6166a9a0b59f70d8a1d445491a858f59801884c1546ec33c7152caeba74ab209355280d3f5b2c8da56d50ad59311ae7531ecabf192ad5b68152cc4beed5702eea3e7ab4bb5c51baa893adb59c4928cc3fa21a568f3e806de3e51647a21a5ea46c4edb3864bd3829781eb9bc27dc7001ca9d1f8bb33dc952095e1e9f55fed5a816195733f5cec3c5929f13ce0a867f2b1482c1691327d25f1d8fca48cec74cc1ade7f1f885a34dbfe61e6a74333da6802a246533d0c7ec05b1e7158ee80ba055b7a2223385296b536a52b8713d03058300c02678472227715940cff76a9cd118e62dab954ea07a5b7e881823cb70311de4bc9943b37930c0a84b9888fea46c798ca0f06437db572b17cea6e67990f51bc885e087864a80b16fe694b7d984427bfe4f1170b53cc2003a27030bb62b8acbd5ee20d036faeaafe241dd8941b366f8a25f93ba13b04fd6dc29fa2211d3699efe14166c61f44613aa85a4a7a1c6f350c064c6eaa33000020ffff4462dcdaaba80cc085c529263c2b1a1435d07c06b8ee5f4adbfd459818540df146798366d0f399fedc8aaec88c9c80ce935ebf6dc19321ca4d48c9f0bc16e3ada797b897747c1f6a389025bc03aba5c706f22f7bd60e4a2d9dabcfe21c3242284d9eca323bee589235ea9d58c07fea06bc0edf26e084ed845bb7353889203c51a431b3b5439dbc519563ee4c5682ba9e04cb0bcf20214b18a84647f07fec24acae5d9961c4a7f32529d6b370d5d4f8a4b0a27e019e1200d38c1a24d0aefa0b6668f81f25b62ee71bc579b7715f2f9b4a5a760e2e33bd2c415b00a3ba87538b27a8a6d5d3686562241f9f5cae596ba554d195cc7bcc7fa3508c21bab57bcd0ba6357e3b8aa5c5c0b82900b3afde2aa2f7fa8d8fe8f005a3a81f24bbb1e0e30e66d95489cca448bd71d614c4cff9d7c680f19f66f9dd30da916dd034ad0a91a37bde89e91cf7f5e4fdc5791b5592911f7af207bcb09bb8606f732b5a5f4746c3f548196e62178bc1183d42dffa76246341c061ca5153d9cd067fd3730ead71c2bc673d73e0b6650efd8f323d8885ab671f7402591a2548d411fa2d5001db8785d01c832a9cdad295f74e632fc3a71908b3f8d3f518add394cff0bc9328950a9aef627f1512091a45cc53ee647a828c3300b75b73ba3dd424235f6e62066c2e6892600020372527ebd1c5c95354693c3b0d6fc187ba8d02538ce9570ba0d174d67a1e659f1c7e469248b8451c3970a0cbb72c0b233688759720c3e5ca111e7e37bf11722cdd31de450f965960031541fd575f7ed979bb934e450e78b541b4859592530e378977e000209143b6b7abbb7fb5773079318ca8150342235db8bcf213115d07d0cc9287930260b3abb9c1e6d2a6934e9eb70e40f7948e4b169c3f76d5ceca028a50b3e9510ab71365dfcf3378bb07164560675874f08db063eb4714dee05c77fa5f8d6ed05e318aee634fca274cf8f0114c0a0697a40705c18d6b30e5fb0bd152d2bae61c1311ded2718e00ac92126dcbdaa5ec666661532eda29a73593ec0f228ab581f1ad93ecc9a15f546c5c808d3b5959187efb7f9b2f65991db38aba88a054b6d3190d16ff638d9f73e26f23331b64c60110a51d7c72c72ec2a8fed638f4fc44cc39e265f157a942e99dd29888565428ed0a41dfb714fee45ac4558fc410412044316bfe18440948dfb3b3327198a747bb22ae5e765a877d6b040ea1cf7a4a660016906a46b0002116796ba8d1a9207251d6652ee56a66a92ad680d5a3f118fdff3eb7710ab45f18acfccf4ecb1251545ab3c706cd45e26f8122ed3ea0c854a017738ff825cc8c672c33e32433a3b8dc4e29524736779f0a5afbf474f624eeda9c5dac1d92e69cb1d5b1098b9e27abad9c159d549dd4cfd92f3042cc5d978ea2e0a59c2fc60e037f27d929283afc8830733b84953fcc09a6b7898581cb557aad4e0c0d7382ba7f1a75483d62d5dd9b28b766653a6e9c726da8972e923f6af7b4d9b09e1cd2c020577f012f1d9b4bff1105e2ec06fd8544953a25b4f49450cffa394fc5f19c7acf66f73bd5e70f8d1f797ef3e35313f2caf28d11289a19555a9ff4fa79f76297d90b4ac4738d40bb833e8eba67bca2a77a878271838bcd7429ebb262ef00ec2911e1b0856f264798762ad1f55d7a4f16f3fa50cbcf03a7616a5748107ba723050f69d4d079f63d6af7a551078ad7257bd114a5170a7f53184ca77740a6c635a2b72fa1c6d8b391a153690bf93683128c2f32b6c1184c3af129ace24283ce7be3c621b6cf6704534de0ca9b7920228ee00fbb8fd84212baf060027a7a528bd9035b9eb197033e7123b23033de72f3fbed553aae9e72b0e6cb7eff2b4c9093ee52c7769bac991cc33622def1741b62edc1e905f343d349d4a9154218f277e29a7ac98e6d6354d61dffe747d6d1e9cce3bf5d2fed56b29098f97d7114c7dd279c0bc36a11edec3f07ec1521277e60d827aeebe708892788cb45df32ed23ec4aa1a093a54933f00020a51fe8366118474665d75508e1ee0adbf6543f0d1637e0941d21678d9e7fc58c1c75c5953f4a327e707d9ce179cd90bb34e3e613164ad5291e2743a423071c9a0e4d858055c6114d73834d1e526e260285af4d0916edaf835c4db48f85265c2877cda83fb467d895928dba9371559c75f203b680c43e9be3f0b7b6161d9b40cd4982386cfa4ee60e33fe31e7ca3a03fc1000dd2b682d0f759e00bdc0a856b7c89fc34d974b11fa731aed3904c8f493dae5e8b916c549e4cb877b800d211a67d324b25f865a36ac940219b6956b775828a714baa6e47a174399176064ca68abbbac9d08f269f3dba75bd7ada2240575ccc41b5a21853889ed25b578f5f3c9446e64d1eff6693caef73f3f32eb142f77eee297d29d3b3180d913106d9f53c3c030a556ea7b8a8e4c8ffadea26e689c7d1fb24e83873e839fa3f94c630ccd5b5567e8e2000020865240246fd0947c50adaeb920f1d6e76496c04d69cbda77442aac5c4e3804072d42af994fc0248ec08a031ff73c2ebbf2712cb6cbe4aa11e0ab2c9a5a464ffea3cda698bbb94d5521917356d3acc433b4bd1102195e3e750bb6d497dee157dfd7e52df6eec97c58ad25259ec30d30fdef43a626c323208a8a24d1f6cdf0210484020d94d36dbedf4e433c46b669418be74ff554cf8f97b6f0680766cb83232832476ecaa4b314452c48db3081f8cabcb56a3fad94f11dce8335330dab89fd8eea6a1cb8b53eb3b09ba5754f86c94758c980596230b96a938ab6375f02272c7967d53865427d08f5cd41239a0cbad23c3353bfaa35868400842c4aadb3857ee1f9a3a0002096d85d6f5df67815847ac96184bfb9df3a4711247c284cabc4fde2907e56de268a8b28ae93aa168d38c938a047ebc59ff14e353f19e120b1e37692a0e0a10ed5435462a187b8cbde501900234e6f6d01023a925bf0cb1a853b37d8cb416f9b941840a315842f55947f978ce713189e59dc9e26e5ffa12ce73fdf53076fd43e490417153cc09872a93c7357fbc86de10b003850aa9b59c65de75b08f3fde82d2438419804eca72daf8daaae1d79efa64f904cef21684b3ab815a47bf80563bd392b1310943f1df000090fb43d466cb4ba81612863a33550334ab6050edb421327397a27016e559999658a2ee9caf0b3b79dfc8d430fd5d67df2605bdf4f42342bb48ae821b96632107fa0ae76d3c8b8a265de9bdd34b1c1c1ec2fd4a2b6ba700e69763000205601ea4503a2ebc95c162ce4c03969ca966a57087cd42cd6e2460bfc622870c2f853764629883af4463c985c64791028cb063f921e4824043af87fc45773ee1ecc0bd70edadeea4580e93a775d0e5b29b8a8f6e9916add7be04742b26dd67017adcfd7e8ac4813078c185776d00eca72fd30f60d83331605c1a380d881205a8c8d0824e666645ffeb1fb2133cf2d11e567bf43a955d3d0b4ec5c3b7ca36bd77b98a13000204f971e429278b863127ef97354cc47a1f394fbb2aaa58d2c8ed3d2427b71cefe3366428a5eab4efcf7a1f224238441c89fe9e4dfa8420b290135faf24f0733209cae82782090e6a35a9d48a27d3d4cd0c2512d5bdc8529c1bc16ded53cd5640c9664f2595db79698b83ea3990985d98c7ec8f93536e0b5e63994d14ea333ba2330000011e8e1a68d3b3fd49e0a298d4199b94b5",
            result.msgToString(),
        )
        assertEquals("", result.sendsToString())
        assertEquals("", result.needsToString())

        // back to client
        result = neClient.reconcile(result.msg!!)

        assertEquals("6186a091c57f0000000002146a05af1884967975a30a83c96915abe34b8b24a0b2cc91791f4207e526be75eab902a1aebb9d1ddb777d1446f8dd6a6f1116d0c3c9836edbc69eb0d3aeebe4c06c02e4c47d13a7616cc4dbdc58d4ec693e9d9baf3126973a94a39b2c8273b3f3eca16b43d3ad92cd16af94be5094287b98cd3957e1ed2a33bba90d0be018591591235f37e2f90794f989ed15bae2c1f733fe402f022ba54c4e3fd370aa1c0aff80961e8b35125646c29a6becdff7be892c155e80edb80618f66963f57775d0e7d4ad2820b0c3bd20f7fa32b1d25f23c7483da3b3eebe53a7f5ef5efe854b3c9d2df893d67354c5ed3957e74ab5ca5fff8c769876054e37dfe18e16c4b206ce8193246d67348837e094593d01e9c734c0965f278b9c7594d2be9586568e391dd4d966df16c619a5349d5b2d0125486f7d25e231ea1d546dd453513e8fab3be610f39dbb2d4075ed702b86de93d4ca486c88d6c9cd35365eea4d10460ce0af3105948eb2e9de6e0d496c7db2d37de8ef929eeeacdd8d2af9bdcb4004df3464a32ca65ac87d67f1087ee07703c16f40f3551f9cabc826cef282d2910a5c0171758a30e6ade789d53f16d953ea47535c171800a779b540d7f5f06d97c0d903ce336d4fccb2f73ad6fb6d372e29f3ea483567e4c3407c67080d8d28061625b03d3f056f8d146c74f5574840e8d82897a757da4b67774eafdd4bc5fdfb3ec6febfccf57b349241e9828effcaeb9039593e65f80906bd5014ed931d6ffa559605858f6c451fc9214992bd951527eecd9ad2b01647c0ceeae55dda7ca234bf573c6d5e2d5144bfaa00b30f7ce4219af75e452c1389ddf2a626390b3f5c96b9c59e5d9f5bc53b6af87340621c7338b02344cb4ae9fe3d44bcf3d525eed1dfbecbb9667a7f", result.msg!!.toHexString())

        // client should send
        var index = 0
        assertEquals("364595abc3da74896b55d9f683de87cdc84cbfd99d0fb13c889fee5d6426becd", result.sendIds[index++].toHexString())
        assertEquals("7113a812c15fc1a8e2a529a6e3b0d412e72b2070b66a58db3a7d31a79199778f", result.sendIds[index++].toHexString())
        assertEquals("33157d5f806a4602de08eb8b23b8a9c1146c0a8d50faacd399daa07f7b16c896", result.sendIds[index++].toHexString())
        assertEquals("a2758ae13d73685f21b7f4c0d9add9e4d96c08f9de63ac757ca977e2f9902c47", result.sendIds[index++].toHexString())
        assertEquals("8a92e9066148bce3b59f5a50d870c55245b11eb12584477a0f5f140b1f498a7e", result.sendIds[index++].toHexString())
        assertEquals("4f6a56eb8b4f92bf5c0fe014c7146107d65f24797b2de861fdc7b3694d343932", result.sendIds[index++].toHexString())
        assertEquals("2dd47629925b956a91aad49812fbea771510ba3f0ec6cb6003e8827df4c4fd9a", result.sendIds[index++].toHexString())
        assertEquals("f34606b7fc924c982f3a024b323cdb932532a8c291afdc3fac994779780ad93c", result.sendIds[index++].toHexString())
        assertEquals("7d5314fc054d0d376625e0d32908db78d6eaf1e1ab167c20873e76d53d60f448", result.sendIds[index++].toHexString())
        assertEquals("88a72a846f7840d693aa55d041d3f237b5181b357bc15a1736793dc7f661c23d", result.sendIds[index++].toHexString())
        assertEquals("fec4a43ce67b34853745c8b2ed3496bdae3684d078d91a515afd6fd60a6e6010", result.sendIds[index++].toHexString())
        assertEquals("f8c2e717ae854234abcd89b852002d52c52101edd690ae305f61d6215ccfd114", result.sendIds[index++].toHexString())
        assertEquals("28840f920e87c1953c431d9e747992005d1c3ccb38d85b2ebe37b0c5ed76acdf", result.sendIds[index++].toHexString())
        assertEquals("eda09054ed0086b1d6c29c0c467ac66851147a373a4d798c0ca9ce4a6f2a30f0", result.sendIds[index++].toHexString())
        assertEquals("d93512968ad530672b25de070f6bce7532cfe546efcdfb423fad7d004901c3d8", result.sendIds[index++].toHexString())
        assertEquals("652ee5f1d1e2c793ab83a2a46c6aeeca64b5ae713fd0e831880476d1e56e5fa3", result.sendIds[index++].toHexString())
        assertEquals("34c7a5f58d1cce2b52507f19bfe77e665c7baacb1fb6946fb78191921ad6c7ca", result.sendIds[index++].toHexString())
        assertEquals("7c37e8e65defa2d8f78e3340318753f598e2fe209dba5a9c3e37b74e9d7158ba", result.sendIds[index++].toHexString())
        assertEquals("9d9242f534b74ca69897011799b0647081aa40e1079ef88b75e925a36f41dfe8", result.sendIds[index++].toHexString())
        assertEquals("afc5021599a80f6c095126d4cb7b280110f792c18da1d43b4ce950d2b5c58d1b", result.sendIds[index++].toHexString())
        assertEquals("0c4555153fd8e36d8e5937099ac6bbafd798ef473eef5cdade6a3e94934e772c", result.sendIds[index++].toHexString())
        assertEquals("765bc775a97cffcd4a5e54f2b9aa2db683b1b5d3bd1d299d55a99de53b3ee401", result.sendIds[index++].toHexString())
        assertEquals("73d5b8039c64c6365383eba4c67594473f5c7af40bc56f4ebc04c0e474fa35c8", result.sendIds[index++].toHexString())
        assertEquals("b020651d499b280712b4dc816c79f2950aa43aaf55417d28b0f08a6bdea25e32", result.sendIds[index++].toHexString())
        assertEquals("80442a0bbefc80b14231d574fedbae57d266fe82253b86d41669320831c04079", result.sendIds[index++].toHexString())
        assertEquals("0840f84309efeb7afd07f094768c30dcb79b0c7df81ed999c9f7fb5935b2ae41", result.sendIds[index++].toHexString())
        assertEquals("639eb9f35053f2fb234ea71ed34af0b4a426ec0d09891419f5366af4ca327a8a", result.sendIds[index++].toHexString())
        assertEquals("1d7907c0a9b578dbd5b4e8bd1003396731daecf88cd17e673aed26a7dd8dbe00", result.sendIds[index++].toHexString())
        assertEquals("dfeeb8ff87c4a32eebfdaacfc3f276c1feca2bf04ee56ad9d2c75c69c5286fd8", result.sendIds[index++].toHexString())
        assertEquals("be8e6d91e7b2dd9423fc2630c0ff92e80c310cec9aef33f929f5998bdf88f6b4", result.sendIds[index++].toHexString())
        assertEquals("45f0d94d25fe0a8bb4a830e5b7cb67cfbb9cdbba567e9a9270819187e987ef06", result.sendIds[index++].toHexString())
        assertEquals("d75ee6bd6800a43bd0d394cb62eff5e3a835b7c6949ee4a178f6295f45290133", result.sendIds[index++].toHexString())
        assertEquals("909c74d65e316d7e192b8bc70cb784b86cd1a2ed8ec68bcd8e17d1878bd51845", result.sendIds[index++].toHexString())
        assertEquals("c5c653c1bee8637d09e092b939b06ec9fa36493e908674340b02444e06010c44", result.sendIds[index++].toHexString())
        assertEquals("70c9a7f5815124d0302bf14e9adedd226a5cf85c1ecbae9b523562340ff97d70", result.sendIds[index++].toHexString())
        assertEquals("431ce78b0ade0172102e76df450d972e83317464b5b496aecf5371730fb7673c", result.sendIds[index++].toHexString())
        assertEquals("ec6668f845225cbda34550182183651bc1c9cbe2e90735d3ded377e66ebe792e", result.sendIds[index++].toHexString())
        assertEquals("f6644052422c7311eec802428bf6c01b657a0860246bb7d05e3eaf9ebc09e78b", result.sendIds[index++].toHexString())
        assertEquals("845073b6b78eb797eec47d36a2bc4703f5e4532182fd5829eb4d46a4ec397ce2", result.sendIds[index++].toHexString())
        assertEquals("455af91b3bb1ea9461032867b4c96cd445ac4813b5c511535272c9267a2aaf4d", result.sendIds[index++].toHexString())
        assertEquals("0d8f4febe3de42db03545c70d6463a132fd5621c408a2ece0a70d9171dae655c", result.sendIds[index++].toHexString())
        assertEquals("23b2f51a318c89c94adafe5d02c59a6a660f0d7cce72222b6b0862a1d50a0946", result.sendIds[index++].toHexString())
        assertEquals("d32e6b996f05bac114883b4285281236dac888d363db700f572c2cb54ced54bd", result.sendIds[index++].toHexString())
        assertEquals("56c528d5b86f64eb4ca2d27b18e1b33acb813370d6e45a55dcdc51eb91689bdd", result.sendIds[index++].toHexString())
        assertEquals("b7a195dda5289bf3610ba7995cd95a10e7102afb24abab7fd6ea52a2c5f5dc28", result.sendIds[index++].toHexString())
        assertEquals("c424b2156fc89107248ce5652a60c4e4e7893179da7a3c986a8e46b12f888bb3", result.sendIds[index++].toHexString())
        assertEquals("c9fbbd91aa2c0dabfd4da986fd1b99abbe9cc412651ffd74e8fcaf74f044c8ab", result.sendIds[index++].toHexString())
        assertEquals("751fbacc09ac3dba52842d39544f5b0874bc72cd9a08871e1862972154354214", result.sendIds[index++].toHexString())
        assertEquals("689447b9bf8a02e144fe6b2e88afa06b6c881ba464fc778c5280f8b67c897437", result.sendIds[index++].toHexString())
        assertEquals("a5d165ce777b6a1a9ff57dcf267886534031265e2108b4e5120d17f11645a830", result.sendIds[index++].toHexString())
        assertEquals("bc2a4ee600a1171fbec67f7871aae2384194119dbd7dd70d5bbe570c7fe28f66", result.sendIds[index++].toHexString())
        assertEquals("7e5694527ad02f317a09a6625854bc950277567ce97d1f387e90b1a3cb565b74", result.sendIds[index++].toHexString())
        assertEquals("a6bf5535e5dd721dc9af8a4859daf7eea23d1f6905f4ec4aaf85b56bc94f62a2", result.sendIds[index++].toHexString())
        assertEquals("0ddbff3bbd6606724c001b99921168e17bfce8aa8f6559686258afbc5bbe54c8", result.sendIds[index++].toHexString())
        assertEquals("4ae3680762b53d0face149134b6fc05a229eccf34a8a18b96d40557e27da5d8c", result.sendIds[index++].toHexString())
        assertEquals("d34abb37d947d3b353c6db8bdbcf4cfc3adece29769d592b4ce56b5c844714f9", result.sendIds[index++].toHexString())
        assertEquals("56d655307987ef847f395cc195340205a28924c78f409d6ac94e90145ce9a17b", result.sendIds[index++].toHexString())
        assertEquals("c9cc8a6056788d6a1eabadcbdee2cc0f92a9cd36a47f2ed6718dc688f0bd9946", result.sendIds[index++].toHexString())
        assertEquals("71feb8cdfe5cb5e3a61550b6d41a395f1537721ce7f831cef2ed270390c28785", result.sendIds[index++].toHexString())
        assertEquals("5f1030ee8e0196621cf35a5cc72d2b8f1ee74e709c0ca5da04e71f4e15dbd5b1", result.sendIds[index++].toHexString())
        assertEquals("107b8cdde998e9e1399c37600cc8abbac68926fbc3bc11002bef6d3589522177", result.sendIds[index++].toHexString())
        assertEquals("f0476fa6ab21d380b8fb4beba4784025e82aa9bb24a7ef3e330c2dfd6427d5df", result.sendIds[index++].toHexString())
        assertEquals("c0095ea999d586833ff7a739fccb30ce4a99bf1765ed4d5b7cf932d14566a561", result.sendIds[index++].toHexString())
        assertEquals("f4fab8e35c81283c2b1a72ae716d20dcfb16a06ea983d59e4e9e9ebc6736dfa9", result.sendIds[index++].toHexString())
        assertEquals("78d9418ba223eb9053b7ed5521ef2b7757691abbf191b0d53a3d4a5bde62672e", result.sendIds[index++].toHexString())
        assertEquals("fc2766edb0075a4f88f9de4295483788ae71eba2504280d516ee33ab29a7ff04", result.sendIds[index++].toHexString())
        assertEquals("29e0dee6fde9a67630c92e484a5dcd017d9916a43e9a9bb3b0271ce92f9323ca", result.sendIds[index++].toHexString())
        assertEquals("51ac2388db67393e294f5c0a59e35f70eafbf61ebe54e1d3b1d190fcc2bbc91f", result.sendIds[index++].toHexString())
        assertEquals("8406d9844fb8f5a5baf3a5cd7478afa6e43d7088b4416dc2013a53fa3447ee95", result.sendIds[index++].toHexString())
        assertEquals("7fa8bb3acb9a17bfe84017044bf164dd18a302f82f42b383c3579fa5d1b45fe1", result.sendIds[index++].toHexString())
        assertEquals("0a7f11de83bb412f9ce914e39510784786d8ab129bbfcd0ea7d8a5ff7c68a51e", result.sendIds[index++].toHexString())
        assertEquals("107732d3e9981632f49ee01ee50aaf32369f8bdb030ff5b5e45ce721725564e1", result.sendIds[index++].toHexString())
        assertEquals("5adfa26ce3af6614bff5cdbc302bf5b03f94f0be0e8fba67a70d2f4ef7b8b1c5", result.sendIds[index++].toHexString())
        assertEquals("ed3784f35f4029450f498d8c3f6acc61d90c7706f77ca1f4f75240f49aafe919", result.sendIds[index++].toHexString())
        assertEquals(74, result.sendIds.size)

        // client should ask
        index = 0
        assertEquals("44d5728f57c4aaae4e5c22a88e9f18781a75dea15dc6157f6487d7fda2abdd4d", result.needIds[index++].toHexString())
        assertEquals("1b7839c6b613519baa8c5930a2d0fcb75e51d09cd6bcbf008db9c2d5e70b11d8", result.needIds[index++].toHexString())
        assertEquals("93a835d4faf5010ac57e563ea44c7d5107e3c5c021834292a68c575a2aad5edf", result.needIds[index++].toHexString())
        assertEquals("ccd2e0e5d0ff68ea4fdcbc78c9430c9e9027fd8151209e53d8d04338441d1311", result.needIds[index++].toHexString())
        assertEquals("a9ee4570ebcd9a5e81054ca50b44c57098214982c639d241045c4ae68687ad0b", result.needIds[index++].toHexString())
        assertEquals("9b5f018c1c508e4ee836445e6c7d0501b5773cb975b73c37a86cb438c1d656df", result.needIds[index++].toHexString())
        assertEquals("1863d6e68af7f2c341787a6301771d07801d110d95ee1ba8b211bd9201781a27", result.needIds[index++].toHexString())
        assertEquals("e28d417be1c0c0242539d1ed7b8428165ecc13a1fa2fa66c9ca48de3f98249c9", result.needIds[index++].toHexString())
        assertEquals("b17e27b1c6cdc9c7c1d4cf538b3ed6f8cc684a4cb8d125838f4cb5fd7f31f1d5", result.needIds[index++].toHexString())
        assertEquals("795fd43ca000350a881c23eba888ef458a940bf355b58ea89daddce28a1ec31a", result.needIds[index++].toHexString())
        assertEquals("ccd8d51741896ab3bfd4f819e4034a267fbbdc0e539ea8a05d53c73ac1312f95", result.needIds[index++].toHexString())
        assertEquals("bed74e251ffaa2ba558efdd868857c251a5017ec2cb25ebd2e18856d3902f8b4", result.needIds[index++].toHexString())
        assertEquals("3d0135483e9f47c64a1e824016845fa4395716947942611f532f52eda322d7d4", result.needIds[index++].toHexString())
        assertEquals("b7c2ad1217c41cca1b0fcbbf4e43ca82ba7d92853065a68dc59c8c048c52e966", result.needIds[index++].toHexString())
        assertEquals("ef3e606c1b9291b7ba1eaa119b431763ae8a41c0da9f60c8df81e6cb85f8fc75", result.needIds[index++].toHexString())
        assertEquals("76c6156c1e307e8337fff6ef8fdedd27d5098d9579210828b6a0c2ba8f08cf71", result.needIds[index++].toHexString())
        assertEquals("273a1fc63de456f9d18d4b63ee02e91dd5330f54b0e44e9e568eecbe093c3f04", result.needIds[index++].toHexString())
        assertEquals("9235ac2e05e532a50723f50b1484edd91cbea1ee9c02e9a7e5fc6ddbf6166a9a", result.needIds[index++].toHexString())
        assertEquals("0b59f70d8a1d445491a858f59801884c1546ec33c7152caeba74ab209355280d", result.needIds[index++].toHexString())
        assertEquals("3f5b2c8da56d50ad59311ae7531ecabf192ad5b68152cc4beed5702eea3e7ab4", result.needIds[index++].toHexString())
        assertEquals("64bd3829781eb9bc27dc7001ca9d1f8bb33dc952095e1e9f55fed5a816195733", result.needIds[index++].toHexString())
        assertEquals("f5cec3c5929f13ce0a867f2b1482c1691327d25f1d8fca48cec74cc1ade7f1f8", result.needIds[index++].toHexString())
        assertEquals("85a34dbfe61e6a74333da6802a246533d0c7ec05b1e7158ee80ba055b7a22233", result.needIds[index++].toHexString())
        assertEquals("85296b536a52b8713d03058300c02678472227715940cff76a9cd118e62dab95", result.needIds[index++].toHexString())
        assertEquals("4ea07a5b7e881823cb70311de4bc9943b37930c0a84b9888fea46c798ca0f064", result.needIds[index++].toHexString())
        assertEquals("37db572b17cea6e67990f51bc885e087864a80b16fe694b7d984427bfe4f1170", result.needIds[index++].toHexString())
        assertEquals("b53cc2003a27030bb62b8acbd5ee20d036faeaafe241dd8941b366f8a25f93ba", result.needIds[index++].toHexString())
        assertEquals("13b04fd6dc29fa2211d3699efe14166c61f44613aa85a4a7a1c6f350c064c6ea", result.needIds[index++].toHexString())
        assertEquals("3ada797b897747c1f6a389025bc03aba5c706f22f7bd60e4a2d9dabcfe21c324", result.needIds[index++].toHexString())
        assertEquals("2284d9eca323bee589235ea9d58c07fea06bc0edf26e084ed845bb7353889203", result.needIds[index++].toHexString())
        assertEquals("c51a431b3b5439dbc519563ee4c5682ba9e04cb0bcf20214b18a84647f07fec2", result.needIds[index++].toHexString())
        assertEquals("4acae5d9961c4a7f32529d6b370d5d4f8a4b0a27e019e1200d38c1a24d0aefa0", result.needIds[index++].toHexString())
        assertEquals("b6668f81f25b62ee71bc579b7715f2f9b4a5a760e2e33bd2c415b00a3ba87538", result.needIds[index++].toHexString())
        assertEquals("b27a8a6d5d3686562241f9f5cae596ba554d195cc7bcc7fa3508c21bab57bcd0", result.needIds[index++].toHexString())
        assertEquals("ba6357e3b8aa5c5c0b82900b3afde2aa2f7fa8d8fe8f005a3a81f24bbb1e0e30", result.needIds[index++].toHexString())
        assertEquals("e66d95489cca448bd71d614c4cff9d7c680f19f66f9dd30da916dd034ad0a91a", result.needIds[index++].toHexString())
        assertEquals("37bde89e91cf7f5e4fdc5791b5592911f7af207bcb09bb8606f732b5a5f4746c", result.needIds[index++].toHexString())
        assertEquals("3f548196e62178bc1183d42dffa76246341c061ca5153d9cd067fd3730ead71c", result.needIds[index++].toHexString())
        assertEquals("2bc673d73e0b6650efd8f323d8885ab671f7402591a2548d411fa2d5001db878", result.needIds[index++].toHexString())
        assertEquals("5d01c832a9cdad295f74e632fc3a71908b3f8d3f518add394cff0bc9328950a9", result.needIds[index++].toHexString())
        assertEquals("aef627f1512091a45cc53ee647a828c3300b75b73ba3dd424235f6e62066c2e6", result.needIds[index++].toHexString())
        assertEquals("d31de450f965960031541fd575f7ed979bb934e450e78b541b4859592530e378", result.needIds[index++].toHexString())
        assertEquals("143b6b7abbb7fb5773079318ca8150342235db8bcf213115d07d0cc928793026", result.needIds[index++].toHexString())
        assertEquals("0b3abb9c1e6d2a6934e9eb70e40f7948e4b169c3f76d5ceca028a50b3e9510ab", result.needIds[index++].toHexString())
        assertEquals("18aee634fca274cf8f0114c0a0697a40705c18d6b30e5fb0bd152d2bae61c131", result.needIds[index++].toHexString())
        assertEquals("1ded2718e00ac92126dcbdaa5ec666661532eda29a73593ec0f228ab581f1ad9", result.needIds[index++].toHexString())
        assertEquals("3ecc9a15f546c5c808d3b5959187efb7f9b2f65991db38aba88a054b6d3190d1", result.needIds[index++].toHexString())
        assertEquals("6ff638d9f73e26f23331b64c60110a51d7c72c72ec2a8fed638f4fc44cc39e26", result.needIds[index++].toHexString())
        assertEquals("e18440948dfb3b3327198a747bb22ae5e765a877d6b040ea1cf7a4a660016906", result.needIds[index++].toHexString())
        assertEquals("cfccf4ecb1251545ab3c706cd45e26f8122ed3ea0c854a017738ff825cc8c672", result.needIds[index++].toHexString())
        assertEquals("c33e32433a3b8dc4e29524736779f0a5afbf474f624eeda9c5dac1d92e69cb1d", result.needIds[index++].toHexString())
        assertEquals("5b1098b9e27abad9c159d549dd4cfd92f3042cc5d978ea2e0a59c2fc60e037f2", result.needIds[index++].toHexString())
        assertEquals("7d929283afc8830733b84953fcc09a6b7898581cb557aad4e0c0d7382ba7f1a7", result.needIds[index++].toHexString())
        assertEquals("5483d62d5dd9b28b766653a6e9c726da8972e923f6af7b4d9b09e1cd2c020577", result.needIds[index++].toHexString())
        assertEquals("f012f1d9b4bff1105e2ec06fd8544953a25b4f49450cffa394fc5f19c7acf66f", result.needIds[index++].toHexString())
        assertEquals("73bd5e70f8d1f797ef3e35313f2caf28d11289a19555a9ff4fa79f76297d90b4", result.needIds[index++].toHexString())
        assertEquals("ac4738d40bb833e8eba67bca2a77a878271838bcd7429ebb262ef00ec2911e1b", result.needIds[index++].toHexString())
        assertEquals("0856f264798762ad1f55d7a4f16f3fa50cbcf03a7616a5748107ba723050f69d", result.needIds[index++].toHexString())
        assertEquals("4d079f63d6af7a551078ad7257bd114a5170a7f53184ca77740a6c635a2b72fa", result.needIds[index++].toHexString())
        assertEquals("1c6d8b391a153690bf93683128c2f32b6c1184c3af129ace24283ce7be3c621b", result.needIds[index++].toHexString())
        assertEquals("6cf6704534de0ca9b7920228ee00fbb8fd84212baf060027a7a528bd9035b9eb", result.needIds[index++].toHexString())
        assertEquals("197033e7123b23033de72f3fbed553aae9e72b0e6cb7eff2b4c9093ee52c7769", result.needIds[index++].toHexString())
        assertEquals("bac991cc33622def1741b62edc1e905f343d349d4a9154218f277e29a7ac98e6", result.needIds[index++].toHexString())
        assertEquals("d6354d61dffe747d6d1e9cce3bf5d2fed56b29098f97d7114c7dd279c0bc36a1", result.needIds[index++].toHexString())
        assertEquals("1edec3f07ec1521277e60d827aeebe708892788cb45df32ed23ec4aa1a093a54", result.needIds[index++].toHexString())
        assertEquals("51fe8366118474665d75508e1ee0adbf6543f0d1637e0941d21678d9e7fc58c1", result.needIds[index++].toHexString())
        assertEquals("c75c5953f4a327e707d9ce179cd90bb34e3e613164ad5291e2743a423071c9a0", result.needIds[index++].toHexString())
        assertEquals("e4d858055c6114d73834d1e526e260285af4d0916edaf835c4db48f85265c287", result.needIds[index++].toHexString())
        assertEquals("7cda83fb467d895928dba9371559c75f203b680c43e9be3f0b7b6161d9b40cd4", result.needIds[index++].toHexString())
        assertEquals("982386cfa4ee60e33fe31e7ca3a03fc1000dd2b682d0f759e00bdc0a856b7c89", result.needIds[index++].toHexString())
        assertEquals("fc34d974b11fa731aed3904c8f493dae5e8b916c549e4cb877b800d211a67d32", result.needIds[index++].toHexString())
        assertEquals("4b25f865a36ac940219b6956b775828a714baa6e47a174399176064ca68abbba", result.needIds[index++].toHexString())
        assertEquals("c9d08f269f3dba75bd7ada2240575ccc41b5a21853889ed25b578f5f3c9446e6", result.needIds[index++].toHexString())
        assertEquals("556ea7b8a8e4c8ffadea26e689c7d1fb24e83873e839fa3f94c630ccd5b5567e", result.needIds[index++].toHexString())
        assertEquals("65240246fd0947c50adaeb920f1d6e76496c04d69cbda77442aac5c4e3804072", result.needIds[index++].toHexString())
        assertEquals("d42af994fc0248ec08a031ff73c2ebbf2712cb6cbe4aa11e0ab2c9a5a464ffea", result.needIds[index++].toHexString())
        assertEquals("3cda698bbb94d5521917356d3acc433b4bd1102195e3e750bb6d497dee157dfd", result.needIds[index++].toHexString())
        assertEquals("4020d94d36dbedf4e433c46b669418be74ff554cf8f97b6f0680766cb8323283", result.needIds[index++].toHexString())
        assertEquals("2476ecaa4b314452c48db3081f8cabcb56a3fad94f11dce8335330dab89fd8ee", result.needIds[index++].toHexString())
        assertEquals("a6a1cb8b53eb3b09ba5754f86c94758c980596230b96a938ab6375f02272c796", result.needIds[index++].toHexString())
        assertEquals("7d53865427d08f5cd41239a0cbad23c3353bfaa35868400842c4aadb3857ee1f", result.needIds[index++].toHexString())
        assertEquals("6d85d6f5df67815847ac96184bfb9df3a4711247c284cabc4fde2907e56de268", result.needIds[index++].toHexString())
        assertEquals("a8b28ae93aa168d38c938a047ebc59ff14e353f19e120b1e37692a0e0a10ed54", result.needIds[index++].toHexString())
        assertEquals("35462a187b8cbde501900234e6f6d01023a925bf0cb1a853b37d8cb416f9b941", result.needIds[index++].toHexString())
        assertEquals("417153cc09872a93c7357fbc86de10b003850aa9b59c65de75b08f3fde82d243", result.needIds[index++].toHexString())
        assertEquals("8419804eca72daf8daaae1d79efa64f904cef21684b3ab815a47bf80563bd392", result.needIds[index++].toHexString())
        assertEquals("b1310943f1df000090fb43d466cb4ba81612863a33550334ab6050edb4213273", result.needIds[index++].toHexString())
        assertEquals("97a27016e559999658a2ee9caf0b3b79dfc8d430fd5d67df2605bdf4f42342bb", result.needIds[index++].toHexString())
        assertEquals("48ae821b96632107fa0ae76d3c8b8a265de9bdd34b1c1c1ec2fd4a2b6ba700e6", result.needIds[index++].toHexString())
        assertEquals("853764629883af4463c985c64791028cb063f921e4824043af87fc45773ee1ec", result.needIds[index++].toHexString())
        assertEquals("c0bd70edadeea4580e93a775d0e5b29b8a8f6e9916add7be04742b26dd67017a", result.needIds[index++].toHexString())
        assertEquals("dcfd7e8ac4813078c185776d00eca72fd30f60d83331605c1a380d881205a8c8", result.needIds[index++].toHexString())
        assertEquals("d0824e666645ffeb1fb2133cf2d11e567bf43a955d3d0b4ec5c3b7ca36bd77b9", result.needIds[index++].toHexString())
        assertEquals("366428a5eab4efcf7a1f224238441c89fe9e4dfa8420b290135faf24f0733209", result.needIds[index++].toHexString())
        assertEquals("cae82782090e6a35a9d48a27d3d4cd0c2512d5bdc8529c1bc16ded53cd5640c9", result.needIds[index++].toHexString())
        assertEquals("664f2595db79698b83ea3990985d98c7ec8f93536e0b5e63994d14ea333ba233", result.needIds[index++].toHexString())
        assertEquals(96, result.needIds.size)

        result = neServer.reconcile(result.msg!!)

        assertEquals("6186a091c57f0000000002226a05af1884967975a30a83c96915abe34b8b24a0b2cc91791f4207e526be75eab902a1aebb9d1ddb777d1446f8dd6a6f1116d0c3c9836edbc69eb0d3aeebe4c091235f37e2f90794f989ed15bae2c1f733fe402f022ba54c4e3fd370aa1c0aff76aa297ea70aaa67754b35fe12050ed570682284cbc11cb8fd9aa893597cf4faed65b3ceb5f1089c8ef7c0d2c10192438d2ca7578e5c9d88dbbc206da5c7b791789be1b3a59bd4730b00e6dfaed3cbbaef6fc65c9ca191531a319b709b9031b3d3042f21be461468b5210539e8ca9da743bc0b8be36b52324b6c38d2bda523aefb4011b226701ee99c8c7d4e544b31a3dd76389d5ced9b339299943fa753ac1b6e609e5d27c7631769a5a94c325d907c4cde89820f47f39045127a4607992982a2df80e7a531529db273955b7da57dbc5011a323a1b145d4cfb4b8a91ecd5bb3962ec5b503fe353567f6242fd7bd1eeeaa4d52921858b5d046868c9f429a5b5b18abb39bda6624bfd4138a3ee8c3c46f111500c255b7e36f631d10f07f9a08143d588304b531fd05ada40956a2426ba6ea339b9ba4702d2a3c1315447fdbf339e10b937f1c4be6462502a2b0899e7ac279e1e33482020f524390a98d2ad32e1093246d67348837e094593d01e9c734c0965f278b9c7594d2be9586568e391dd4d966df16c619a5349d5b2d0125486f7d25e231ea1d546dd453513e8fab3be610b360d2260eabf4a55740fa531dea04513604edaa745ad3af8d53ca0ff4adae669bcfd810d7e6f09db593c3ab547c447766a51f3d9ffdaf871bf0bfd1b0cdcedfa5b2419cd60033fedcc0c3932c9528dd654d5124b579c57f482b17a46caddaa7b57e2c729c79ec6e569593a0b3535f9330f7beb19941089047bb492b0a8b387b277d1fa2d7b3cdad8e1917ff02bedc707c9307d8b06c981b43facedd79fb0cd4a578f8947ac32ec126833da2fd0c684ed24594a2f29b42d05cc99f6f91f1bf3c24fabb7c747d65aa60d6d844bfbc5463d566cebb888073e8a609a6b39aa8ad38bba1b17d8d4ab4fb11b3e5dafb9d9dc4d7529577aa403a29087141f1093d97f935bb870a4764bdd79166f7847d53270e9910fdb54cb38bdf99fef0d627040df230e6ade789d53f16d953ea47535c171800a779b540d7f5f06d97c0d903ce336d03dd0683541f1132a8db9f6351118da790150168193129569978ce544833de3fc820f4c1784abda82e640fc20cbfe0f7f0544da4cfe74d382392d73375d2503d5650fc157cb1c947b00cee120fd71b6e93dc6dfdb87f092f3d0c52b31d74675c451fc9214992bd951527eecd9ad2b01647c0ceeae55dda7ca234bf573c6d5e2dd324d78f55e0b12e7b65b2c8549750e2bd2102a96180f5b1de2d72189d6fec734e2f7ab8c7701ef642f4d114bc96c50dbd0f3b5c7be9fc84f28e1a1ec7eb2ccb8e57ea6406d2886d9dcd02c340855a72c683d63867add8dc97e37e1c1dada0f7a8ffcf834d601f8bb92ec8f8fc1206159f8944e94476c5970ab1f81d3ca11d97", result.msg!!.toHexString())

        result = neClient.reconcile(result.msg!!)

        // Finishes in the 2nd round
        assertEquals(null, result.msg)

        index = 0
        assertEquals("6c02e4c47d13a7616cc4dbdc58d4ec693e9d9baf3126973a94a39b2c8273b3f3", result.sendIds[index++].toHexString())
        assertEquals("eca16b43d3ad92cd16af94be5094287b98cd3957e1ed2a33bba90d0be0185915", result.sendIds[index++].toHexString())
        assertEquals("80961e8b35125646c29a6becdff7be892c155e80edb80618f66963f57775d0e7", result.sendIds[index++].toHexString())
        assertEquals("d4ad2820b0c3bd20f7fa32b1d25f23c7483da3b3eebe53a7f5ef5efe854b3c9d", result.sendIds[index++].toHexString())
        assertEquals("2df893d67354c5ed3957e74ab5ca5fff8c769876054e37dfe18e16c4b206ce81", result.sendIds[index++].toHexString())
        assertEquals("f39dbb2d4075ed702b86de93d4ca486c88d6c9cd35365eea4d10460ce0af3105", result.sendIds[index++].toHexString())
        assertEquals("948eb2e9de6e0d496c7db2d37de8ef929eeeacdd8d2af9bdcb4004df3464a32c", result.sendIds[index++].toHexString())
        assertEquals("a65ac87d67f1087ee07703c16f40f3551f9cabc826cef282d2910a5c0171758a", result.sendIds[index++].toHexString())
        assertEquals("4fccb2f73ad6fb6d372e29f3ea483567e4c3407c67080d8d28061625b03d3f05", result.sendIds[index++].toHexString())
        assertEquals("6f8d146c74f5574840e8d82897a757da4b67774eafdd4bc5fdfb3ec6febfccf5", result.sendIds[index++].toHexString())
        assertEquals("7b349241e9828effcaeb9039593e65f80906bd5014ed931d6ffa559605858f6c", result.sendIds[index++].toHexString())
        assertEquals("5144bfaa00b30f7ce4219af75e452c1389ddf2a626390b3f5c96b9c59e5d9f5b", result.sendIds[index++].toHexString())
        assertEquals("c53b6af87340621c7338b02344cb4ae9fe3d44bcf3d525eed1dfbecbb9667a7f", result.sendIds[index++].toHexString())
        assertEquals(13, result.sendIds.size)

        // client should ask
        index = 0
        assertEquals("76aa297ea70aaa67754b35fe12050ed570682284cbc11cb8fd9aa893597cf4fa", result.needIds[index++].toHexString())
        assertEquals("ed65b3ceb5f1089c8ef7c0d2c10192438d2ca7578e5c9d88dbbc206da5c7b791", result.needIds[index++].toHexString())
        assertEquals("789be1b3a59bd4730b00e6dfaed3cbbaef6fc65c9ca191531a319b709b9031b3", result.needIds[index++].toHexString())
        assertEquals("d3042f21be461468b5210539e8ca9da743bc0b8be36b52324b6c38d2bda523ae", result.needIds[index++].toHexString())
        assertEquals("fb4011b226701ee99c8c7d4e544b31a3dd76389d5ced9b339299943fa753ac1b", result.needIds[index++].toHexString())
        assertEquals("6e609e5d27c7631769a5a94c325d907c4cde89820f47f39045127a4607992982", result.needIds[index++].toHexString())
        assertEquals("a2df80e7a531529db273955b7da57dbc5011a323a1b145d4cfb4b8a91ecd5bb3", result.needIds[index++].toHexString())
        assertEquals("962ec5b503fe353567f6242fd7bd1eeeaa4d52921858b5d046868c9f429a5b5b", result.needIds[index++].toHexString())
        assertEquals("18abb39bda6624bfd4138a3ee8c3c46f111500c255b7e36f631d10f07f9a0814", result.needIds[index++].toHexString())
        assertEquals("3d588304b531fd05ada40956a2426ba6ea339b9ba4702d2a3c1315447fdbf339", result.needIds[index++].toHexString())
        assertEquals("e10b937f1c4be6462502a2b0899e7ac279e1e33482020f524390a98d2ad32e10", result.needIds[index++].toHexString())
        assertEquals("b360d2260eabf4a55740fa531dea04513604edaa745ad3af8d53ca0ff4adae66", result.needIds[index++].toHexString())
        assertEquals("9bcfd810d7e6f09db593c3ab547c447766a51f3d9ffdaf871bf0bfd1b0cdcedf", result.needIds[index++].toHexString())
        assertEquals("a5b2419cd60033fedcc0c3932c9528dd654d5124b579c57f482b17a46caddaa7", result.needIds[index++].toHexString())
        assertEquals("b57e2c729c79ec6e569593a0b3535f9330f7beb19941089047bb492b0a8b387b", result.needIds[index++].toHexString())
        assertEquals("277d1fa2d7b3cdad8e1917ff02bedc707c9307d8b06c981b43facedd79fb0cd4", result.needIds[index++].toHexString())
        assertEquals("a578f8947ac32ec126833da2fd0c684ed24594a2f29b42d05cc99f6f91f1bf3c", result.needIds[index++].toHexString())
        assertEquals("24fabb7c747d65aa60d6d844bfbc5463d566cebb888073e8a609a6b39aa8ad38", result.needIds[index++].toHexString())
        assertEquals("bba1b17d8d4ab4fb11b3e5dafb9d9dc4d7529577aa403a29087141f1093d97f9", result.needIds[index++].toHexString())
        assertEquals("35bb870a4764bdd79166f7847d53270e9910fdb54cb38bdf99fef0d627040df2", result.needIds[index++].toHexString())
        assertEquals("03dd0683541f1132a8db9f6351118da790150168193129569978ce544833de3f", result.needIds[index++].toHexString())
        assertEquals("c820f4c1784abda82e640fc20cbfe0f7f0544da4cfe74d382392d73375d2503d", result.needIds[index++].toHexString())
        assertEquals("5650fc157cb1c947b00cee120fd71b6e93dc6dfdb87f092f3d0c52b31d74675c", result.needIds[index++].toHexString())
        assertEquals("d324d78f55e0b12e7b65b2c8549750e2bd2102a96180f5b1de2d72189d6fec73", result.needIds[index++].toHexString())
        assertEquals("4e2f7ab8c7701ef642f4d114bc96c50dbd0f3b5c7be9fc84f28e1a1ec7eb2ccb", result.needIds[index++].toHexString())
        assertEquals("8e57ea6406d2886d9dcd02c340855a72c683d63867add8dc97e37e1c1dada0f7", result.needIds[index++].toHexString())
        assertEquals("a8ffcf834d601f8bb92ec8f8fc1206159f8944e94476c5970ab1f81d3ca11d97", result.needIds[index++].toHexString())
        assertEquals(27, result.needIds.size)
    }
}
