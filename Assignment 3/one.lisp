(DEFUN sumOfDigits (x) 
	(IF (= x 0) 
		0
		(+ (mod x 10) (sumOfDigits (floor x 10)))))

(DEFUN isPrime (n &optional (d (floor (sqrt n)))) 
	(COND
		((< n 2) nil)
		((= d 1) t)
		((= 0 (mod n d)) nil)
		(t (isPrime n (- d 1)))))

(DEFUN generateRandom (filename out_file)
	(with-open-file (stream out_file :direction :output)
	(let ((in (open filename :if-does-not-exist nil)))
	   (when in
	      (loop for line = (read-line in nil)
	      while line do (IF (isPrime (sumOfdigits (parse-integer line))) (format stream "~a~%" line)))
	      (close in)
	   ))))
