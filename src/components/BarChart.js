import { Bar } from "react-chartjs-2";


export default function BarChart({ chartData }){
  return (
    <div className="chart-container">
      <Bar
        data={chartData}
        options={{
          plugins: {
            title: {
              display: true,
              text: "IMDb Ratings across their career"
            },
            legend: {
              display: false
            }
          },
          scales: {
            x: {
              title: {
                display: true,
                text: 'Rating'
              }
            },
            y: {
              title: {
                display: true,
                text: 'Amount'
              }
            }
          }
        }}
      />
    </div>
  );
};








  