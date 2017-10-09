<script>
    import { Line, Bar } from 'vue-chartjs'
    export default Line.extend({
        name: 'tasks-graph',
        props: {
            points: []
        },
        mounted () {
            // Overwriting base render method with actual data.
            this.renderChart(
                this.points,
                {
                    responsive: true,
                    title:{
                        display:true,
                        text: 'Chart.js Line Chart'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Month'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Value'
                            }
                        }]
                    }
                }
            );
        }
    })
</script>
