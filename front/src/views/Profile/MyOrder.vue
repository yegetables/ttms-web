<template>
    <div>
        <div class="container">
            <div class="order">我的订单</div>

            <div class="content" orderid="1" v-for="item in orders" :key="item.userOrder.id">

                <div class="header">
                    <span class="date">{{ item.userOrder.payTime }}</span>
                    <span style="color: #999;">西柚订单号:{{ item.userOrder.id }}</span>
                    <span v-if="compareDate(item.userOrder.movieStartTime) && item.hallSeat != null"
                        @click='deleteOrder(item.userOrder.id)' class="del" orderid="1">退票</span>
                </div>

                <div class="order-body">
                    <div class="poster">
                        <img :src="item.userOrder.movieHead">
                    </div>
                    <div class="order-content">
                        <div class="moviename">{{ item.userOrder.movieName }}</div>
                        <div v-if="item.hallSeat != null" class="seat">
                            座位:{{ item.hallSeat.seatLine }}排{{ item.hallSeat.seatColumn }}座</div>
                        <div class="seat" v-else> 未查询到演出计划和座位信息，可能已删除</div>
                        <div class="showdate">演出时间：{{ item.userOrder.movieStartTime }}</div>
                        <div class="order-price">￥{{ item.userOrder.orderMoney }}</div>
                        <div class="order-status">{{ item.userOrder.orderStatus }}</div>
                    </div>
                </div>

            </div>


        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            orders: []
        }
    },
    methods: {
        // date
        compareDate(startdate) {
            var StarDate = new Date(startdate);
            if (StarDate.getTime() < new Date().getTime()) {
                console.log('flase');
                return false;
            }
            console.log('true');
            return true;
        },
        deleteOrder(id) {
            this.$api.deleteOrder({
                idList: id
            }).then(res => {
                // console.log(res);
                if (res.data.code === 0) {
                    this.$router.go(0)
                    this.$message({
                        message: '恭喜你，退票成功',
                        type: 'success'
                    });
                }
            })
        }
    },
    created() {
        this.$api.getOrder().then(res => {
            // console.log(res)
            if (res.data.code === 0) {
                this.orders = res.data.data
                console.log(this.orders);
            }
        })
    }
}
</script>

<style scoped>
.container {
    padding-left: 40px;
    width: 922px;
    min-height: 900px;

}

.order {
    color: #ec443f;
    font-size: 18px;
    padding: 26px 0;
    border-bottom: 1px solid #e1e1e1;
    margin-bottom: 30px;
}

.content {
    border: 1px solid #e5e5e5;
    margin: 0 40px 30px 0;
}

.content .order-body>div {
    display: inline-block;
    vertical-align: top;
}

.header {
    background-color: #f7f7f7;
    font-size: 14px;
    padding: 16px 20px;
}

.header .date {
    color: #333;
    margin-right: 30px;
    display: inline-block;
}

.order-body {
    padding: 20px;
    padding-right: 0;
}

.order-body .poster {
    font-size: 0;
    margin-right: 11px;
    width: 90px;
    height: 110px;
}

.order-body .poster img {
    width: 100%;
    height: 100%;
}

.order-body .order-content {
    width: 49%;
}

.content .order-body .moviename {
    font-size: 16px;
    font-weight: 700;
    color: #333;
    margin: 4px 0 7px -6px;
}

.content .order-body .seat {
    font-size: 12px;
    color: #999;
    margin-bottom: 4px;
}

.content .order-body .showdate {
    font-size: 12px;
    color: #f03d37;
}

.content .order-body .order-price {
    font-size: 14px;
    color: #333;
    width: 12%;
    line-height: 95px;
}

.content .order-body .order-status {
    width: 15%;
    font-size: 14px;
    color: #333;
    line-height: 95px;
}

.content .order-body .order-price,
.order-status {
    display: inline-block;
    vertical-align: top;
}

.content .header .del {
    font-size: 14px;
    color: #fff;
    background-color: #ec443f;
    border-radius: 20%;
    border: 1px solid #ec443f;
    width: 46px;
    height: 25px;
    line-height: 25px;
    text-align: center;
    float: right;
    /* background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAQCAYAAADJViUEAAAAAXNSR0IArs4c6QAAAP5JREFUKBVjZMACZs2alfTv3z95kNT///9FOTg4qhITEz+gK2UEKlQDKhBEklAG8ksZGRk3QDWzAPmhLCws0UADWWDqBAUFz7MABVKAAvYwQaBCVnZ2drekpKTXMLEZM2Y8/fv37wwg/x9M7MuXLz4wNnk00NTkmTNnppGiG6jnCEg9E9BvMkCnSsM0T58+/T8+NlAtE1DeCqQGxCAbjGomMegoDrAvwLgzglmamZnJiI89Z84cdaB6sBpgemeZ9evXrx5g4rgLTDALgRqZYZrRaaA8858/f6qBdCZIDmwCMHmyAjNIDpDPz8TEBE9h6JqBNv4Fih3PyMjYC5IDACkobU1JvS12AAAAAElFTkSuQmCC) no-repeat; */
}

.order-content {
    position: relative;
}

.order-content .order-price {
    position: absolute;
    top: -1px;
    right: 18px;
}

.order-content .order-status {
    position: absolute;
    top: -2px;
    right: -160px;
}
</style>